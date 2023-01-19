package kodlama.io.Universty.webApi.controllers;

import kodlama.io.Universty.business.abstracts.StudentService;
import kodlama.io.Universty.core.utilities.results.DataResult;
import kodlama.io.Universty.core.utilities.results.Result;
import kodlama.io.Universty.webApi.model.requests.student.StudentAddRequest;
import kodlama.io.Universty.webApi.model.requests.student.StudentUpdateRequest;
import kodlama.io.Universty.webApi.model.responses.student.GetAllStudentResponse;
import kodlama.io.Universty.webApi.model.responses.student.GetByIdStudentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentControllers {

  private StudentService studentService;

  public StudentControllers(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping("/getall")
  public ResponseEntity<DataResult<List<GetAllStudentResponse>>> getAll() {
    return new ResponseEntity<>(studentService.getAll(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DataResult<GetByIdStudentResponse>> getById(@PathVariable int id)
      throws Exception {
    return new ResponseEntity<>(studentService.getById(id), HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<Result> add(@RequestBody StudentAddRequest studentAddRequest)
      throws Exception {
    return new ResponseEntity<>(studentService.add(studentAddRequest), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Result> update(
      @PathVariable int id, @RequestBody StudentUpdateRequest studentUpdateRequest)
      throws Exception {
    return new ResponseEntity<>(studentService.update(id, studentUpdateRequest), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Result> delete(@PathVariable int id) throws Exception {
    return new ResponseEntity<>(studentService.delete(id), HttpStatus.OK);
  }
}
