package kodlama.io.Universty.business.concretes;

import kodlama.io.Universty.business.abstracts.DepartmentService;
import kodlama.io.Universty.business.constants.Messages;
import kodlama.io.Universty.core.utilities.customExceptions.BusinessException;
import kodlama.io.Universty.core.utilities.results.DataResult;
import kodlama.io.Universty.core.utilities.results.Result;
import kodlama.io.Universty.core.utilities.results.SuccessDataResult;
import kodlama.io.Universty.core.utilities.results.SuccessResult;
import kodlama.io.Universty.dataAccess.abstracts.DepartmentRepository;
import kodlama.io.Universty.entities.concretes.Department;
import kodlama.io.Universty.webApi.model.requests.department.DepartmentAddRequest;
import kodlama.io.Universty.webApi.model.requests.department.DepartmentUpdateRequest;
import kodlama.io.Universty.webApi.model.responses.department.GetAllDepartmentResponse;
import kodlama.io.Universty.webApi.model.responses.department.GetByIdDepartmentResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentManager implements DepartmentService {

  private DepartmentRepository departmentRepository;

  public DepartmentManager(DepartmentRepository departmentRepository) {
    this.departmentRepository = departmentRepository;
  }

  @Override
  public DataResult<List<GetAllDepartmentResponse>> getAll() {

    List<Department> departments = departmentRepository.findAll();
    List<GetAllDepartmentResponse> getAllDepartmentResponseList = new ArrayList<>();
    for (Department inDbDepartment : departments) {
      GetAllDepartmentResponse getAllDepartmentResponse =
          buildGetAllDepartmentResponseFromDepartment(inDbDepartment);
      getAllDepartmentResponseList.add(getAllDepartmentResponse);
    }
    return new SuccessDataResult<>(
        getAllDepartmentResponseList, Messages.GetListMessages.DEPARTMENTS_LISTED);
  }

  @Override
  public DataResult<GetByIdDepartmentResponse> getById(int id) throws Exception {

    Department department =
        departmentRepository
            .findById(id)
            .orElseThrow(() -> new BusinessException(Messages.ErrorMessages.ID_NOT_FOUND));
    GetByIdDepartmentResponse getByIdDepartmentResponse =
        buildGetByIdDepartmentResponseFromDepartment(department);
    return new SuccessDataResult<>(
        getByIdDepartmentResponse, Messages.GetByIdMessages.DEPARTMENT_BROUGHT_SUCCESSFULLY);
  }

  @Override
  public Result add(DepartmentAddRequest departmentAddRequest) throws Exception {

    existsByDepartmentName(departmentAddRequest.getName());
    Department department = buildDepartmentAddRequestToDepartment(departmentAddRequest);
    departmentRepository.save(department);
    return new SuccessResult(
        Messages.AddMessages.DEPARTMENT_ADDED + " " + departmentAddRequest.getName());
  }

  @Override
  public Result update(int id, DepartmentUpdateRequest departmentUpdateRequest) throws Exception {

    existsByDepartmentName(departmentUpdateRequest.getName());
    Department inDbDepartment = departmentRepository.findById(id).orElseThrow(() -> new BusinessException(Messages.ErrorMessages.ID_NOT_FOUND));
    Department department = buildDepartmentUpdateRequestToDepartment(departmentUpdateRequest);
    department.setId(inDbDepartment.getId());
    departmentRepository.save(department);
    return new SuccessResult(
        Messages.UpdateMessages.DEPARTMENT_UPDATED + " " + departmentUpdateRequest.getName());
  }

  @Override
  public Result delete(int id) throws Exception {

    Department department =
        departmentRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException(Messages.ErrorMessages.ID_NOT_FOUND));
    departmentRepository.deleteById(id);
    return new SuccessResult(
        Messages.DeleteMessages.DEPARTMENT_DELETED + " " + department.getName());
  }

  @Override
  public Department getDepartmentById(int id) {
    return departmentRepository
        .findById(id)
        .orElseThrow(() -> new BusinessException(Messages.ErrorMessages.ID_NOT_FOUND));
  }

  @Override
  public void isDepartmentExists(int id) {
    if (!departmentRepository.existsById(id)) {
      throw new BusinessException(Messages.ErrorMessages.ID_NOT_FOUND);
    }
  }

  private void existsByDepartmentName(String name) {
    if (departmentRepository.existsByName(name)) {
      throw new BusinessException(Messages.ErrorMessages.DEPARTMENT_NAME_DUPLICATED);
    }
  }

  private static GetAllDepartmentResponse buildGetAllDepartmentResponseFromDepartment(
      Department inDbDepartment) {
    return GetAllDepartmentResponse.builder()
        .departmentId(inDbDepartment.getId())
        .departmentName(inDbDepartment.getName())
        .description(inDbDepartment.getDescription())
        .build();
  }

  private static GetByIdDepartmentResponse buildGetByIdDepartmentResponseFromDepartment(
      Department inDbDepartment) {
    return GetByIdDepartmentResponse.builder()
        .departmentName(inDbDepartment.getName())
        .description(inDbDepartment.getDescription())
        .build();
  }

  private static Department buildDepartmentAddRequestToDepartment(
      DepartmentAddRequest departmentAddRequest) {
    return Department.builder()
        .name(departmentAddRequest.getName())
        .description(departmentAddRequest.getDescription())
        .build();
  }

  private static Department buildDepartmentUpdateRequestToDepartment(
      DepartmentUpdateRequest departmentUpdateRequest) {
    return Department.builder()
        .name(departmentUpdateRequest.getName())
        .description(departmentUpdateRequest.getDescription())
        .build();
  }
}
