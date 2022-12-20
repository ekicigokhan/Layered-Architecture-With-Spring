package kodlama.io.Universty.webApi.controllers;

import kodlama.io.Universty.business.abstracts.BranchService;
import kodlama.io.Universty.core.utilities.results.DataResult;
import kodlama.io.Universty.core.utilities.results.Result;
import kodlama.io.Universty.webApi.model.requests.branch.BranchAddRequest;
import kodlama.io.Universty.webApi.model.requests.branch.BranchUpdateRequest;
import kodlama.io.Universty.webApi.model.responses.branch.GetAllBranchResponse;
import kodlama.io.Universty.webApi.model.responses.branch.GetByIdBranchResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/branches")
public class BranchController {

  private BranchService branchService;

  public BranchController(BranchService branchService) {
    this.branchService = branchService;
  }

  @GetMapping("/getall")
  public ResponseEntity<DataResult> getAll() {

    DataResult<List<GetAllBranchResponse>> branchServiceGetAll = branchService.getAll();
    return new ResponseEntity<>(branchServiceGetAll, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DataResult> getById(@PathVariable int id) throws Exception {

    DataResult<GetByIdBranchResponse> branchServicegetById = branchService.getById(id);
    return new ResponseEntity<>(branchServicegetById, HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<Result> add(@RequestBody BranchAddRequest branchAddRequest)
      throws Exception {
    Result branchServiceAddResult = branchService.add(branchAddRequest);
    return new ResponseEntity<>(branchServiceAddResult, HttpStatus.OK);
  }

  @PutMapping("{id}")
  public ResponseEntity<Result> update(
      @PathVariable int id, @RequestBody BranchUpdateRequest branchUpdateRequest) throws Exception {
    Result branchServiceUpdateResult = branchService.update(id, branchUpdateRequest);
    return new ResponseEntity<>(branchServiceUpdateResult, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Result> delete(@PathVariable int id) throws Exception {
    Result branchServiceDeleteResult = branchService.delete(id);
    return new ResponseEntity<>(branchServiceDeleteResult, HttpStatus.OK);
  }
}
