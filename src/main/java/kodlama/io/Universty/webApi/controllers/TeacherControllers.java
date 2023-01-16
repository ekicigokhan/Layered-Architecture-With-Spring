package kodlama.io.Universty.webApi.controllers;

import kodlama.io.Universty.business.abstracts.TeacherService;
import kodlama.io.Universty.core.utilities.results.DataResult;
import kodlama.io.Universty.core.utilities.results.Result;
import kodlama.io.Universty.webApi.model.requests.teacher.TeacherAddRequest;
import kodlama.io.Universty.webApi.model.requests.teacher.TeacherUpdateRequest;
import kodlama.io.Universty.webApi.model.responses.teacher.GetAllTeacherResponse;
import kodlama.io.Universty.webApi.model.responses.teacher.GetByIdTeacherResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherControllers {

  private TeacherService teacherService;

  public TeacherControllers(TeacherService teacherService) {

    this.teacherService = teacherService;
  }

  @GetMapping("/getall")
  public ResponseEntity<DataResult> getAll() {

    DataResult<List<GetAllTeacherResponse>> getAllTeacherResponseDataResult =
        teacherService.getAll();
    return new ResponseEntity<>(getAllTeacherResponseDataResult, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DataResult> getById(@PathVariable int id) throws Exception {

    DataResult<GetByIdTeacherResponse> teacherServiceGetById = teacherService.getById(id);
    return new ResponseEntity<>(teacherServiceGetById, HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<Result> add(@Valid @RequestBody TeacherAddRequest teacherAddRequest)
      throws Exception {

    Result teacherServiceAddResult = teacherService.add(teacherAddRequest);
    return new ResponseEntity<>(teacherServiceAddResult, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Result> update(
          @PathVariable int id, @Valid@RequestBody TeacherUpdateRequest teacherUpdateRequest)
      throws Exception {

    Result teacherServiceUpdateResult = teacherService.update(id, teacherUpdateRequest);
    return new ResponseEntity<>(teacherServiceUpdateResult, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Result> delete(@PathVariable int id) throws Exception {

    Result teacherServiceDeleteResult = teacherService.delete(id);
    return new ResponseEntity<>(teacherServiceDeleteResult, HttpStatus.OK);
  }
}
