package kodlama.io.Universty.webApi.controllers;

import kodlama.io.Universty.business.abstracts.FacultyService;
import kodlama.io.Universty.core.utilities.results.DataResult;
import kodlama.io.Universty.core.utilities.results.Result;
import kodlama.io.Universty.webApi.model.requests.faculty.FacultyAddRequest;
import kodlama.io.Universty.webApi.model.requests.faculty.FacultyUpdateRequest;
import kodlama.io.Universty.webApi.model.requests.student.StudentAddRequest;
import kodlama.io.Universty.webApi.model.requests.student.StudentUpdateRequest;
import kodlama.io.Universty.webApi.model.responses.faculty.GetAllFacultyResponse;
import kodlama.io.Universty.webApi.model.responses.faculty.GetByIdFacultyResponse;
import kodlama.io.Universty.webApi.model.responses.student.GetByIdStudentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/faculties")
public class FacultyControllers {
    private FacultyService facultyService;

    public FacultyControllers(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/getall")
    public ResponseEntity<DataResult<List<GetAllFacultyResponse>>> getAll() {
        return new ResponseEntity<>(facultyService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<GetByIdFacultyResponse>> getById(@PathVariable int id)
            throws Exception {
        return new ResponseEntity<>(facultyService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody FacultyAddRequest facultyAddRequest)
            throws Exception {
        return new ResponseEntity<>(facultyService.add(facultyAddRequest), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> update(
            @PathVariable int id, @RequestBody FacultyUpdateRequest facultyUpdateRequest)
            throws Exception {
        return new ResponseEntity<>(facultyService.update(id, facultyUpdateRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable int id) throws Exception {
        return new ResponseEntity<>(facultyService.delete(id), HttpStatus.OK);
    }
}
