package kodlama.io.Universty.business.concretes;

import kodlama.io.Universty.business.abstracts.BranchService;
import kodlama.io.Universty.business.abstracts.TeacherService;
import kodlama.io.Universty.core.utilities.customExceptions.BusinessException;
import kodlama.io.Universty.core.utilities.results.DataResult;
import kodlama.io.Universty.core.utilities.results.Result;
import kodlama.io.Universty.core.utilities.results.SuccessDataResult;
import kodlama.io.Universty.core.utilities.results.SuccessResult;
import kodlama.io.Universty.dataAccess.abstracts.BranchRepository;
import kodlama.io.Universty.entities.concretes.Branch;
import kodlama.io.Universty.entities.concretes.Teacher;
import kodlama.io.Universty.webApi.model.requests.branch.BranchAddRequest;
import kodlama.io.Universty.webApi.model.requests.branch.BranchUpdateRequest;
import kodlama.io.Universty.webApi.model.responses.branch.GetAllBranchResponse;
import kodlama.io.Universty.webApi.model.responses.branch.GetByIdBranchResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchManager implements BranchService {

  private BranchRepository branchRepository;

  public BranchManager(BranchRepository branchRepository) {
    this.branchRepository = branchRepository;
  }

  @Override
  public DataResult<List<GetAllBranchResponse>> getAll() {

    List<GetAllBranchResponse> branchResponses =
        branchRepository.findAll().stream()
            .map(branch -> new GetAllBranchResponse(branch.getId(), branch.getName()))
            .toList();
    return new SuccessDataResult<>(branchResponses, "BRANŞLAR BAŞARIYLA LİSTELENDİ ! ");
  }

  @Override
  public DataResult<GetByIdBranchResponse> getById(int id) {

    GetByIdBranchResponse getByIdBranchResponse = new GetByIdBranchResponse();
    Branch branch =
        branchRepository
            .findById(id)
            .orElseThrow(
                () -> new RuntimeException("SİSTEMDE BU ID İLE KAYITLI BRANŞ BULUNAMADI."));
    getByIdBranchResponse.setBranchName(branch.getName());

    return new SuccessDataResult<>(getByIdBranchResponse, "SİSTEMDE "+id+" NOLU ID İLE TANIMLANMIŞ ÖĞRETMEN BAŞARIYLA GETİRİLDİ ! ");
  }

  @Override
  public Result add(BranchAddRequest branchAddRequest) throws Exception {
    ;
    isNameExist(branchAddRequest.getName());

    Branch branch = new Branch();
    branch.setName(branchAddRequest.getName());

    branchRepository.save(branch);
    return new SuccessResult(branchAddRequest.getName() + " İSİMLİ BRANŞ BAŞARIYLA EKLENDİ.");
  }

  @Override
  public Result update(int id, BranchUpdateRequest branchUpdateRequest) throws Exception {

    isIdExist(id);
    isNameExist(branchUpdateRequest.getNewBranchName());

    Branch branch =
        branchRepository
            .findById(id)
            .orElseThrow(
                () -> new RuntimeException("SİSTEMDE BU ID İLE KAYITLI BRANŞ BULUNAMADI."));
    branch.setName(branchUpdateRequest.getNewBranchName());

    branchRepository.save(branch);

    return new SuccessResult(branchUpdateRequest.getNewBranchName() + " İSİMLİ DERS GÜNCELLEMESİ BAŞARILI !");
  }

  @Override
  public Result delete(int id) throws Exception {

    isIdExist(id);

    branchRepository.deleteById(id);

    return new SuccessResult(id + " NUMARALI ID SİLİNDİ.");
  }

  @Override
  public Branch getBranchById(int id) {
    return branchRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("BRANCH BULUNAMADI !"));
  }

  public void isNameExist(String name) throws Exception {
    for (Branch branch : branchRepository.findAll()) {
      if (branch.getName().equalsIgnoreCase(name)) {

        throw new BusinessException("SİSTEMDE AYNI İSİMDE BRANŞ ZATEN TANIMLI ! ");
      }
    }
  }

  public void isIdExist(int id) throws Exception {
    for (Branch branch : branchRepository.findAll()) {
      if (branch.getId() != id) {
        throw new BusinessException("SİSTEMDE BU İD İLE TANIMLI BRANŞ BULUNAMADI ! ");
      }
    }
  }
}
