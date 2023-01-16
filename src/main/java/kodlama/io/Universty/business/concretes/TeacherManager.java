package kodlama.io.Universty.business.concretes;

import kodlama.io.Universty.business.abstracts.BranchService;
import kodlama.io.Universty.business.abstracts.TeacherService;
import kodlama.io.Universty.business.constants.Messages;
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

import java.util.ArrayList;
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

    List<Teacher> teachers = teacherRepository.findAll();
    List<GetAllTeacherResponse> teacherResponseList = new ArrayList<>();
    for (Teacher inDbTeacher : teachers) {
      GetAllTeacherResponse getAllTeacherResponse =
          buildGetAllTeacherResponseFromTeacher(inDbTeacher);
      teacherResponseList.add(getAllTeacherResponse);
    }
    return new SuccessDataResult<>(teacherResponseList, Messages.GetListMessages.TEACHERS_LISTED);
  }

  @Override
  public DataResult<GetByIdTeacherResponse> getById(int id) throws Exception {

    Teacher inDbTeacher =
        teacherRepository
            .findById(id)
            .orElseThrow(() -> new BusinessException(Messages.ErrorMessages.ID_NOT_FOUND));
    GetByIdTeacherResponse getByIdTeacherResponse =
        buildGetByIdTeacherResponseFromTeacher(inDbTeacher);
    return new SuccessDataResult<>(
        getByIdTeacherResponse,
        Messages.GetByIdMessages.TEACHER_BROUGHT_SUCCESSFULLY
            + " "
            + getByIdTeacherResponse.getFirstName()
            + " "
            + getByIdTeacherResponse.getLastName());
  }

  @Override
  public Result add(TeacherAddRequest teacherAddRequest) throws Exception {

    branchService.isBranchExists(teacherAddRequest.getBranchId());
    existsByFirstNameAndLastNameIgnoreCaseAndBranch_Id(
        teacherAddRequest.getFirstName(),
        teacherAddRequest.getLastName(),
        teacherAddRequest.getBranchId());
    Branch branch = branchService.getBranchById(teacherAddRequest.getBranchId());
    Teacher teacher = buildTeacherAddRequestToTeacher(teacherAddRequest);
    teacher.setBranch(branch);
    teacherRepository.save(teacher);
    return new SuccessResult(
        Messages.AddMessages.TEACHER_ADDED
            + " "
            + teacherAddRequest.getFirstName()
            + " "
            + teacherAddRequest.getLastName());
  }

  @Override
  public Result update(int id, TeacherUpdateRequest teacherUpdateRequest) throws Exception {

    Teacher inDbTeacher =
        teacherRepository
            .findById(id)
            .orElseThrow(() -> new BusinessException(Messages.ErrorMessages.ID_NOT_FOUND));
    Branch branch = branchService.getBranchById(teacherUpdateRequest.getBranchId());
    Teacher teacher = buildTeacherUpdateRequestToTeacher(teacherUpdateRequest);
    teacher.setId(inDbTeacher.getId());
    teacher.setBranch(branch);
    teacherRepository.save(teacher);
    return new SuccessResult(Messages.UpdateMessages.TEACHER_UPDATED);
  }

  @Override
  public Result delete(int id) throws Exception {

    isTeacherExists(id);
    Teacher teacher = teacherRepository.findById(id).get();
    teacherRepository.deleteById(id);
    return new SuccessResult(
        Messages.DeleteMessages.TEACHER_DELETED
            + " "
            + teacher.getFirstName()
            + " "
            + teacher.getLastName());
  }

  private void isTeacherExists(int id) {
    if (!teacherRepository.existsById(id)) {
      throw new BusinessException(Messages.ErrorMessages.ID_NOT_FOUND);
    }
  }

  private void existsByFirstNameAndLastNameIgnoreCaseAndBranch_Id(
      String firstName, String lastName, int id) {
    if (teacherRepository.existsByFirstNameAndLastNameIgnoreCaseAndBranch_Id(
        firstName, lastName, id)) {
      throw new BusinessException(Messages.ErrorMessages.TEACHER_NAME_DUPLICATED);
    }
  }

  private static GetAllTeacherResponse buildGetAllTeacherResponseFromTeacher(Teacher inDbTeacher) {
    return GetAllTeacherResponse.builder()
        .id(inDbTeacher.getId())
        .firstName(inDbTeacher.getFirstName())
        .lastName(inDbTeacher.getLastName())
        .age(inDbTeacher.getAge())
        .biography(inDbTeacher.getBiography())
        .title(inDbTeacher.getTitle())
        .salary(inDbTeacher.getSalary())
        .gender(inDbTeacher.getGender())
        .email(inDbTeacher.getEmail())
        .userName(inDbTeacher.getUserName())
        .password(inDbTeacher.getPassword())
        .branch(inDbTeacher.getBranch())
        .build();
  }

  private static GetByIdTeacherResponse buildGetByIdTeacherResponseFromTeacher(
      Teacher inDbTeacher) {
    return GetByIdTeacherResponse.builder()
        .branchName(inDbTeacher.getBranch().getName())
        .firstName(inDbTeacher.getFirstName())
        .lastName(inDbTeacher.getLastName())
        .age(inDbTeacher.getAge())
        .biography(inDbTeacher.getBiography())
        .title(inDbTeacher.getTitle())
        .salary(inDbTeacher.getSalary())
        .biography(inDbTeacher.getBiography())
        .build();
  }

  private static Teacher buildTeacherAddRequestToTeacher(TeacherAddRequest teacherAddRequest) {

    return Teacher.builder()
        .firstName(teacherAddRequest.getFirstName())
        .lastName(teacherAddRequest.getLastName())
        .age(teacherAddRequest.getAge())
        .biography(teacherAddRequest.getBiography())
        .title(teacherAddRequest.getTitle())
        .salary(teacherAddRequest.getSalary())
        .gender(teacherAddRequest.getGender())
        .email(teacherAddRequest.getEmail())
        .userName(teacherAddRequest.getUserName())
        .password(teacherAddRequest.getPassword())
        .build();
  }

  private static Teacher buildTeacherUpdateRequestToTeacher(
      TeacherUpdateRequest teacherUpdateRequest) {
    return Teacher.builder()
        .firstName(teacherUpdateRequest.getFirstName())
        .lastName(teacherUpdateRequest.getLastName())
        .age(teacherUpdateRequest.getAge())
        .biography(teacherUpdateRequest.getBiography())
        .title(teacherUpdateRequest.getTitle())
        .salary(teacherUpdateRequest.getSalary())
        .gender(teacherUpdateRequest.getGender())
        .email(teacherUpdateRequest.getEmail())
        .userName(teacherUpdateRequest.getUserName())
        .password(teacherUpdateRequest.getPassword())
        .build();
  }
}
