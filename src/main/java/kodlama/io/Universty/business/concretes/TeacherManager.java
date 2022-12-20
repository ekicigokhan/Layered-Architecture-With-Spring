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

import java.util.List;
import java.util.Objects;

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
                        teacher.getId(), teacher.getFirstName(),teacher.getLastName(), teacher.getBranch()))
            .toList(),
        "ÖĞRETMENLER BAŞARIYLA LİSTELENDİ !");
  }

  @Override
  public DataResult<GetByIdTeacherResponse> getById(int id) throws Exception {

    GetByIdTeacherResponse getByIdTeacherResponse = new GetByIdTeacherResponse();
    Teacher teacher =
        teacherRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new BusinessException("SİSTEMDE BU ID İLE TANIMLANMIŞ ÖĞRETMEN BULUNAMADI.."));
    getByIdTeacherResponse.setFirstname(teacher.getFirstName());
    getByIdTeacherResponse.setLastName(teacher.getLastName());
    getByIdTeacherResponse.setBranchName(teacher.getBranch().getName());
    return new SuccessDataResult<>(getByIdTeacherResponse, id + " NUMARALI VERİ GETİRİLDİ.");
  }

  @Override
  public Result add(TeacherAddRequest teacherAddRequest) throws Exception {

    if (!this.branchService.isBranchExists(teacherAddRequest.getBranchId())) {
      throw new BusinessException("BU ID İLE TANIMLI ÖĞRETMEN BULUNAMADI !");
    }
    if (existsByFirstNameIgnoreCaseAndBranch_Id(
        teacherAddRequest.getFirstName(), teacherAddRequest.getBranchId())) {
      throw new BusinessException("EKLEMEK İSTEDİĞİNİZ ÖĞRETMEN BU BRANŞTA ZATEN TANIMLI ! ");
    }

    Teacher teacher = new Teacher();
    teacher.setFirstName(teacherAddRequest.getFirstName());
    teacher.setLastName(teacherAddRequest.getLastName());
    teacher.setGender(teacherAddRequest.getGender());
    teacher.setEmail(teacherAddRequest.getEmail());
    teacher.setPassword(teacherAddRequest.getPassword());
    teacher.setAge(teacherAddRequest.getAge());
    teacher.setBiography(teacherAddRequest.getBiography());
    teacher.setSalary(teacherAddRequest.getSalary());
    teacher.setTitle(teacherAddRequest.getTitle());
    teacher.setUserName(teacherAddRequest.getUserName());

    Branch branch = this.branchService.getBranchById(teacherAddRequest.getBranchId());
    if (Objects.nonNull(teacher)) {
      teacher.setBranch(branch);
    }
    teacherRepository.save(teacher);

    return new SuccessResult(teacherAddRequest.getFirstName() +" "+ teacherAddRequest.getLastName() + " BAŞARIYLA SİSTEME EKLENDİ ! ");
  }

  @Override
  public Result update(int id, TeacherUpdateRequest teacherUpdateRequest) throws Exception {

    if (!existsById(id)){
      throw new BusinessException("BU ID İLE TANIMLI ÖĞRETMEN BULUNAMADI !");
    }

    if (existsByName(teacherUpdateRequest.getFirstName())){
      throw new BusinessException("GÜNCELLEMEK İSTEDİĞİNİZ ÖĞRETMEN BU BRANŞTA ZATEN TANIMLI ! ");
    }

    Teacher teacher =
        teacherRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new BusinessException("SİSTEMDE BU ID İLE TANIMLANMIŞ ÖĞRETMEN BULUNAMADI.."));
    teacher.setFirstName(teacherUpdateRequest.getFirstName());
    teacher.setLastName(teacherUpdateRequest.getLastName());

    teacherRepository.save(teacher);

    return new SuccessResult("BAŞARIYLA GÜNCELLENDİ !");
  }

  @Override
  public Result delete(int id) throws Exception {

    if (!existsById(id)){
      throw new BusinessException("BU ID İLE TANIMLI ÖĞRETMEN BULUNAMADI !");
    }

    teacherRepository.deleteById(id);
    return new SuccessResult("SİLME İŞLEMİ BAŞARILI !");
  }

  public boolean existsByName(String name) {
    return teacherRepository.existsByFirstName(name);
  }

  public boolean existsById(int id) {
    return teacherRepository.existsById(id);
  }

  boolean existsByFirstNameIgnoreCaseAndBranch_Id(String name, int id) {
    return teacherRepository.existsByFirstNameIgnoreCaseAndBranch_Id(name, id);
  }
}
