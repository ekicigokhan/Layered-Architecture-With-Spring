package kodlama.io.Universty.business.concretes;

import kodlama.io.Universty.business.abstracts.DepartmentService;
import kodlama.io.Universty.business.abstracts.StudentService;
import kodlama.io.Universty.business.constants.Messages;
import kodlama.io.Universty.core.utilities.customExceptions.BusinessException;
import kodlama.io.Universty.core.utilities.results.DataResult;
import kodlama.io.Universty.core.utilities.results.Result;
import kodlama.io.Universty.core.utilities.results.SuccessDataResult;
import kodlama.io.Universty.core.utilities.results.SuccessResult;
import kodlama.io.Universty.dataAccess.abstracts.StudentRepository;
import kodlama.io.Universty.entities.concretes.Department;
import kodlama.io.Universty.entities.concretes.Student;
import kodlama.io.Universty.webApi.model.requests.student.StudentAddRequest;
import kodlama.io.Universty.webApi.model.requests.student.StudentUpdateRequest;
import kodlama.io.Universty.webApi.model.responses.student.GetAllStudentResponse;
import kodlama.io.Universty.webApi.model.responses.student.GetByIdStudentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class StudentManager implements StudentService {

    private StudentRepository studentRepository;
    private DepartmentService departmentService;

    public StudentManager(StudentRepository studentRepository, DepartmentService departmentService) {
        this.studentRepository = studentRepository;
        this.departmentService = departmentService;
    }

    @Override
    public DataResult<List<GetAllStudentResponse>> getAll() {

        List<Student> students = studentRepository.findAll();
        List<GetAllStudentResponse> getAllStudentResponseList = new ArrayList<>();
        for (Student inDbStudent : students) {
            GetAllStudentResponse getAllStudentResponse =
                    buildGetAllStudentResponseFromStudent(inDbStudent);
            getAllStudentResponseList.add(getAllStudentResponse);
        }
        return new SuccessDataResult<>(
                getAllStudentResponseList, Messages.GetListMessages.STUDENTS_LISTED);
    }

    @Override
    public DataResult<GetByIdStudentResponse> getById(int id) throws Exception {

        Student inDbStudent =
                studentRepository
                        .findById(id)
                        .orElseThrow(() -> new BusinessException(Messages.ErrorMessages.ID_NOT_FOUND));
        GetByIdStudentResponse getByIdStudentResponse =
                buildGetByIdStudentResponseFromStudent(inDbStudent);
        return new SuccessDataResult<>(
                getByIdStudentResponse,
                Messages.GetByIdMessages.TEACHER_BROUGHT_SUCCESSFULLY
                        + " "
                        + inDbStudent.getFirstName()
                        + " "
                        + inDbStudent.getLastName());
    }

    @Override
    public Result add(StudentAddRequest studentAddRequest) throws Exception {

        departmentService.isDepartmentExists(studentAddRequest.getDepartmentId());
        isEmailExists(studentAddRequest.getEmail());
        existsByFirstNameAndLastNameIgnoreCaseAndDepartment_Id(
                studentAddRequest.getFirstName(),
                studentAddRequest.getLastName(),
                studentAddRequest.getDepartmentId());
        Department department =
                departmentService.getDepartmentById(studentAddRequest.getDepartmentId());
        Student student = buildStudentAddRequestToStudent(studentAddRequest);
        student.setDepartment(department);
        studentRepository.save(student);
        return new SuccessResult(
                Messages.AddMessages.STUDENT_ADDED
                        + " "
                        + student.getFirstName()
                        + " "
                        + student.getLastName());
    }

    @Override
    public Result update(int id, StudentUpdateRequest studentUpdateRequest) throws Exception {

        Student inDbStudent =
                studentRepository
                        .findById(id)
                        .orElseThrow(() -> new BusinessException(Messages.ErrorMessages.ID_NOT_FOUND));
        Department department = departmentService.getDepartmentById(studentUpdateRequest.getDepartmentId());
        Student student = buildStudentUpdateRequestToStudent(studentUpdateRequest);
        student.setId(inDbStudent.getId());
        student.setDepartment(department);
        studentRepository.save(student);
        return new SuccessResult(Messages.UpdateMessages.STUDENT_UPDATED);
    }

    @Override
    public Result delete(int id) throws Exception {
        Student student =
                studentRepository
                        .findById(id)
                        .orElseThrow(() -> new BusinessException(Messages.ErrorMessages.ID_NOT_FOUND));
        studentRepository.deleteById(id);
        return new SuccessResult(
                Messages.DeleteMessages.STUDENT_DELETED
                        + student.getFirstName()
                        + " "
                        + student.getLastName());
    }

    private void isEmailExists(String email) {
        for (Student inDbStudent : studentRepository.findAll())
        if (inDbStudent.getEmail().equalsIgnoreCase(email)) {
            throw new BusinessException(Messages.ErrorMessages.MAİL_DUPLICATED);
        }
    }

    private void isStudentExists(int id) {
        if (!studentRepository.existsById(id)) {
            throw new BusinessException(Messages.ErrorMessages.ID_NOT_FOUND);
        }
    }

    private void existsByFirstNameAndLastNameIgnoreCaseAndDepartment_Id(
            String firstName, String lastName, int id) {
        if (studentRepository.existsByFirstNameAndLastNameIgnoreCaseAndDepartment_Id(
                firstName, lastName, id)) {
            throw new BusinessException(Messages.ErrorMessages.STUDENT_NAME_DUPLICATED);
        }
    }

    private static GetAllStudentResponse buildGetAllStudentResponseFromStudent(Student inDbStudent) {
        return GetAllStudentResponse.builder()
                .id(inDbStudent.getId())
                .firstName(inDbStudent.getFirstName())
                .lastName(inDbStudent.getLastName())
                .studentNo(inDbStudent.getStudentNo())
                .nationality(inDbStudent.getNationality())
                .nationalIdentity(inDbStudent.getNationalIdentity())
                .birthday(inDbStudent.getBirthday())
                .gender(inDbStudent.getGender())
                .email(inDbStudent.getEmail())
                .userName(inDbStudent.getUserName())
                .password(inDbStudent.getPassword())
                .department(inDbStudent.getDepartment())
                .lessons(inDbStudent.getLessons())
                .build();
    }

    private static GetByIdStudentResponse buildGetByIdStudentResponseFromStudent(
            Student inDbStudent) {
        return GetByIdStudentResponse.builder()
                .firstName(inDbStudent.getFirstName())
                .lastName(inDbStudent.getLastName())
                .departmentName(inDbStudent.getDepartment().getName())
                .studentNo(inDbStudent.getStudentNo())
                .nationality(inDbStudent.getNationality())
                .birthday(inDbStudent.getBirthday())
                .gender(inDbStudent.getGender())
                .email(inDbStudent.getEmail())
                .build();
    }

    private static Student buildStudentAddRequestToStudent(StudentAddRequest studentAddRequest) {
        return Student.builder()
                .firstName(studentAddRequest.getFirstName())
                .lastName(studentAddRequest.getLastName())
                .studentNo(studentAddRequest.getStudentNo())
                .nationality(studentAddRequest.getNationality())
                .nationalIdentity(studentAddRequest.getNationalIdentity())
                .birthday(studentAddRequest.getBirthday())
                .gender(studentAddRequest.getGender())
                .email(studentAddRequest.getEmail())
                .userName(studentAddRequest.getUserName())
                .password(studentAddRequest.getPassword())
                .lessons(studentAddRequest.getLessons())
                .build();
    }

    private static Student buildStudentUpdateRequestToStudent(
            StudentUpdateRequest studentUpdateRequest) {
        return Student.builder()
                .firstName(studentUpdateRequest.getFirstName())
                .lastName(studentUpdateRequest.getLastName())
                .studentNo(studentUpdateRequest.getStudentNo())
                .nationality(studentUpdateRequest.getNationality())
                .nationalIdentity(studentUpdateRequest.getNationalIdentity())
                .birthday(studentUpdateRequest.getBirthday())
                .gender(studentUpdateRequest.getGender())
                .email(studentUpdateRequest.getEmail())
                .userName(studentUpdateRequest.getUserName())
                .password(studentUpdateRequest.getPassword())
                .build();
    }
}
