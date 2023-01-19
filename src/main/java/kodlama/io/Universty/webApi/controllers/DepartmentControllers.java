package kodlama.io.Universty.webApi.controllers;

import kodlama.io.Universty.business.abstracts.DepartmentService;
import kodlama.io.Universty.core.utilities.results.DataResult;
import kodlama.io.Universty.core.utilities.results.Result;
import kodlama.io.Universty.webApi.model.requests.department.DepartmentAddRequest;
import kodlama.io.Universty.webApi.model.requests.department.DepartmentUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/departments")
public class DepartmentControllers {

    private DepartmentService departmentService;

    public DepartmentControllers(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/getall")
    public ResponseEntity<DataResult> getAll(){

        DataResult departmentServiceGetAllResult = departmentService.getAll();
        return new ResponseEntity<>(departmentServiceGetAllResult, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult> getById(@PathVariable int id) throws Exception {

        DataResult departmentServiceGetByıdResult = departmentService.getById(id);
        return new ResponseEntity<>(departmentServiceGetByıdResult,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody DepartmentAddRequest departmentAddRequest) throws Exception {

        Result departmentServiceAddResult =  departmentService.add(departmentAddRequest);
        return new ResponseEntity<>(departmentServiceAddResult,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> update(@PathVariable int id, @Valid @RequestBody DepartmentUpdateRequest departmentUpdateRequest) throws Exception {

        Result departmentServiceUpdateResult =  departmentService.update(id,departmentUpdateRequest);
        return new ResponseEntity<>(departmentServiceUpdateResult,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable int id) throws Exception {

        Result departmentServiceDeleteResult =  departmentService.delete(id);
        return new ResponseEntity<>(departmentServiceDeleteResult,HttpStatus.OK);
    }

}
