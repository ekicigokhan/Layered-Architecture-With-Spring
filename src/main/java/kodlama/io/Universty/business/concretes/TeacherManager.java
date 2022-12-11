package kodlama.io.Universty.business.concretes;

import kodlama.io.Universty.business.abstracts.BranchService;
import kodlama.io.Universty.business.abstracts.TeacherService;
import kodlama.io.Universty.core.utilities.customExceptions.BusinessException;
import kodlama.io.Universty.core.utilities.results.DataResult;
import kodlama.io.Universty.core.utilities.results.Result;
import kodlama.io.Universty.core.utilities.results.SuccessDataResult;
import kodlama.io.Universty.core.utilities.results.SuccessResult;
import kodlama.io.Universty.dataAccess.abstracts.TeacherRepository;
import kodlama.io.Universty.entities.concretes.Branch;
import kodlama.io.Universty.entities.concretes.Teacher;
import kodlama.io.Universty.webApi.model.requests.teacher.TeacherAddRequest;
import kodlama.io.Universty.webApi.model.requests.teacher.TeacherUpdateRequest;
import kodlama.io.Universty.webApi.model.responses.teacher.GetAllTeacherResponse;
import kodlama.io.Universty.webApi.model.responses.teacher.GetByIdTeacherResponse;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TeacherManager implements TeacherService {

  private TeacherRepository teacherRepository;
  private BranchService branchService;

  public TeacherManager(TeacherRepository teacherRepository, BranchService branchService) {

    this.teacherRepository = teacherRepository;
    this.branchService = branchService;
  }

  @Override
  public DataResult<List<GetAllTeacherResponse>> getAll() {

    return new SuccessDataResult<>(
        teacherRepository.findAll().stream()
            .map(
                teacher ->
                    new GetAllTeacherResponse(
                        teacher.getId(), teacher.getName(), teacher.getBranch()))
            .toList(),
        "ÖĞRETMENLER BAŞARIYLA LİSTELENDİ !");
  }

  @Override
  public DataResult<GetByIdTeacherResponse> getById(int id) {

    GetByIdTeacherResponse getByIdTeacherResponse = new GetByIdTeacherResponse();
    Teacher teacher =
        teacherRepository
            .findById(id)
            .orElseThrow(
                () -> new RuntimeException("SİSTEMDE BU ID İLE TANIMLANMIŞ ÖĞRETMEN BULUNAMADI.."));
    getByIdTeacherResponse.setName(teacher.getName());
    getByIdTeacherResponse.setBranchName(teacher.getBranch().getName());
    return new SuccessDataResult<>(getByIdTeacherResponse, id + " NUMARALI VERİ GETİRİLDİ.");
  }

  @Override
  public Result add(TeacherAddRequest teacherAddRequest) throws Exception {

    isNameExist(teacherAddRequest.getName());
    isIdExist(teacherAddRequest.getBranchId());

    Teacher teacher = new Teacher();
    teacher.setName(teacherAddRequest.getName());
    Branch branch = this.branchService.getBranchById(teacherAddRequest.getBranchId());
    teacher.setBranch(branch);

    teacherRepository.save(teacher);

    return new SuccessResult(teacherAddRequest.getName() + " BAŞARIYLA SİSTEME EKLENDİ ! ");
  }

  @Override
  public Result update(int id, TeacherUpdateRequest teacherUpdateRequest) throws Exception {

    isIdExist(id);
    isIdExist(teacherUpdateRequest.getBranchId());
    isNameExist(teacherUpdateRequest.getName());

    Teacher teacher =
        teacherRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        "SİSTEMDE BU ID İLE TANIMLANMIŞ ÖĞRETMEN BULUNAMADI.."));
    teacher.setName(teacherUpdateRequest.getName());
    Branch branch = this.branchService.getBranchById(teacherUpdateRequest.getBranchId());
    teacher.setBranch(branch);

    teacherRepository.save(teacher);

    return new SuccessResult("BAŞARIYLA GÜNCELLENDİ !");
  }

  @Override
  public Result delete(int id) throws Exception {

    isIdExist(id);

    teacherRepository.deleteById(id);
    return new SuccessResult("SİLME İŞLEMİ BAŞARILI !");
  }

  public void isNameExist(String name) throws Exception {
    for (Teacher teacher : teacherRepository.findAll()) {
      if (teacher.getName().equalsIgnoreCase(name)) {

        throw new BusinessException("Sistemde aynı isimde öğretmen zaten tanımlı ! ");
      }
    }
  }

  public void isIdExist(int id) throws Exception {
    for (Teacher teacher : teacherRepository.findAll()) {
      if (teacher.getId() != id) {
        throw new BusinessException("Sistemde bu ID ile tanımlı öğretmen bulunamadı ! ");
      }
    }
  }
}
