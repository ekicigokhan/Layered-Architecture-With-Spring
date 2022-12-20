package kodlama.io.Universty.business.concretes;

import kodlama.io.Universty.business.abstracts.BranchService;
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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
  public DataResult<GetByIdBranchResponse> getById(int id) throws Exception {

    GetByIdBranchResponse getByIdBranchResponse = new GetByIdBranchResponse();
    Branch branch =
        branchRepository
            .findById(id)
            .orElseThrow(
                () -> new BusinessException("SİSTEMDE BU ID İLE KAYITLI BRANŞ BULUNAMADI."));
    getByIdBranchResponse.setBranchName(branch.getName());

    return new SuccessDataResult<>(
        getByIdBranchResponse,
        "SİSTEMDE " + id + " NOLU ID İLE TANIMLANMIŞ BRANŞ BAŞARIYLA GETİRİLDİ ! ");
  }

  @Override
  public Result add(BranchAddRequest branchAddRequest) throws Exception {

    if (existsByName(branchAddRequest.getName())) {
      throw new BusinessException("EKLEMEK İSTEDİĞİNİZ BRANŞ ZATEN MEVCUT !");
    }

    Branch branch = new Branch();
    branch.setName(branchAddRequest.getName());

    branchRepository.save(branch);
    return new SuccessResult(branchAddRequest.getName() + " İSİMLİ BRANŞ BAŞARIYLA EKLENDİ.");
  }

  @Override
  public Result update(int id, BranchUpdateRequest branchUpdateRequest) throws Exception {

    if (!existsById(id) || existsByName(branchUpdateRequest.getNewBranchName())) {
      throw new BusinessException(
          "GÜNCELLEMEK İSTEDİĞİNİZ ID MEVCUT DEĞİL VEYA AYNI İSİMDE BRANŞ ZATEN TANIMLANMIŞ !");
    }

    Branch branch = branchRepository.findById(id).get();
    branch.setName(branchUpdateRequest.getNewBranchName());

    branchRepository.save(branch);

    return new SuccessResult(
        branchUpdateRequest.getNewBranchName() + " İSİMLİ DERS GÜNCELLEMESİ BAŞARILI !");
  }

  @Override
  public Result delete(int id) throws Exception {

    if (!existsById(id)) {
      throw new BusinessException("SİLMEK İSTEDİĞİNİZ ID MEVCUT DEĞİL !");
    }
    Branch branch = branchRepository.findById(id).get();
    branchRepository.deleteById(id);


    return new SuccessResult(id + " NUMARALI ID SİLİNDİ. SİLİNEN BRANŞ: "+ branch.getName() );
  }

  @Override
  public Branch getBranchById(int id) {

    return branchRepository
        .findById(id)
        .orElseThrow(() -> new BusinessException("BU ID İLE TANIMLI BRANŞ BULUNAMADI !"));
  }

  @Override
  public boolean isBranchExists(int id) {
    return existsById(id);
  }


  public boolean existsById(int id) {
    return branchRepository.existsById(id);
  }

  public boolean existsByName(String name) {
    return branchRepository.existsByName(name);
  }
}
