package kodlama.io.Universty.business.concretes;

import kodlama.io.Universty.business.abstracts.BranchService;
import kodlama.io.Universty.business.constants.Messages;
import kodlama.io.Universty.core.utilities.customExceptions.BusinessException;
import kodlama.io.Universty.core.utilities.results.DataResult;
import kodlama.io.Universty.core.utilities.results.Result;
import kodlama.io.Universty.core.utilities.results.SuccessDataResult;
import kodlama.io.Universty.core.utilities.results.SuccessResult;
import kodlama.io.Universty.dataAccess.abstracts.BranchRepository;
import kodlama.io.Universty.entities.concretes.Branch;
import kodlama.io.Universty.webApi.model.requests.branch.BranchAddRequest;
import kodlama.io.Universty.webApi.model.requests.branch.BranchUpdateRequest;
import kodlama.io.Universty.webApi.model.responses.branch.GetAllBranchResponse;
import kodlama.io.Universty.webApi.model.responses.branch.GetByIdBranchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BranchManager implements BranchService {

  private BranchRepository branchRepository;

  public BranchManager(BranchRepository branchRepository) {
    this.branchRepository = branchRepository;
  }

  @Override
  public DataResult<List<GetAllBranchResponse>> getAll() {

    List<Branch> branches = branchRepository.findAll();
    List<GetAllBranchResponse> getAllBranchResponseList = new ArrayList<>();
    for (Branch inDbBranch : branches) {
      GetAllBranchResponse getAllBranchResponse =
          buildGetAllBranchResponseFromBranch(inDbBranch);
      getAllBranchResponseList.add(getAllBranchResponse);
    }

    return new SuccessDataResult<>(
        getAllBranchResponseList, Messages.GetListMessages.BRANCHES_LISTED);
  }

  @Override
  public DataResult<GetByIdBranchResponse> getById(int id) throws Exception {
    Branch inDbBranch =
        branchRepository
            .findById(id)
            .orElseThrow(() -> new BusinessException(Messages.ErrorMessages.ID_NOT_FOUND));
    GetByIdBranchResponse getByIdBranchResponse =
        buildGetByIdBranchResponseFromBranch(inDbBranch);
    return new SuccessDataResult<>(
        getByIdBranchResponse, Messages.GetByIdMessages.BRANCH_BROUGHT_SUCCESSFULLY);
  }

  @Override
  public Result add(BranchAddRequest branchAddRequest) throws Exception {

    existsByBranchName(branchAddRequest.getName());
    Branch branch = buildBranchAddRequestToBranch(branchAddRequest);
    branchRepository.save(branch);
    log.info("added {} ", branchAddRequest.getName());
    return new SuccessResult(Messages.AddMessages.BRANCH_ADDED + branchAddRequest.getName());
  }

  @Override
  public Result update(int id, BranchUpdateRequest branchUpdateRequest) throws Exception {

    isBranchExists(id);
    existsByBranchName(branchUpdateRequest.getName());
    Branch inDbBranch = branchRepository.findById(id).get();
    Branch branch = buildBranchUpdateRequestToBranch(branchUpdateRequest);
    branch.setId(inDbBranch.getId());
    branchRepository.save(branch);

    return new SuccessResult(
        Messages.UpdateMessages.BRANCH_UPDATED + " " + branchUpdateRequest.getName());
  }

  @Override
  public Result delete(int id) throws Exception {

    Branch branch =
        branchRepository
            .findById(id)
            .orElseThrow(() -> new BusinessException(Messages.ErrorMessages.ID_NOT_FOUND));
    branchRepository.deleteById(id);

    return new SuccessResult(Messages.DeleteMessages.BRANCH_DELETED + " " + branch.getName());
  }

  @Override
  public Branch getBranchById(int id) {

    return branchRepository
        .findById(id)
        .orElseThrow(() -> new BusinessException(Messages.ErrorMessages.ID_NOT_FOUND));
  }

  @Override
  public void isBranchExists(int id) {
    if (!branchRepository.existsById(id)) {
      throw new BusinessException(Messages.ErrorMessages.ID_NOT_FOUND);
    }
  }

  private void existsByBranchName(String name) {
    if (branchRepository.existsByName(name)) {
      throw new BusinessException(Messages.ErrorMessages.BRANCH_NAME_DUPLICATED);
    }
  }

  private static GetAllBranchResponse buildGetAllBranchResponseFromBranch(
          Branch inDbBranch) {
    return GetAllBranchResponse.builder()
        .branchId(inDbBranch.getId())
        .branchName(inDbBranch.getName())
        .build();
  }

  private static GetByIdBranchResponse buildGetByIdBranchResponseFromBranch(
          Branch inDbBranch) {
    return GetByIdBranchResponse.builder()
            .branchName(inDbBranch.getName())
            .build();
  }

  private static Branch buildBranchAddRequestToBranch(
          BranchAddRequest branchAddRequest) {
    return Branch.builder()
            .name(branchAddRequest.getName())
            .build();
  }

  private static Branch buildBranchUpdateRequestToBranch(
          BranchUpdateRequest branchUpdateRequest) {
    return Branch.builder()
            .name(branchUpdateRequest.getName())
            .build();
  }
}
