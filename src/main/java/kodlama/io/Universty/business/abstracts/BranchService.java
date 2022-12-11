package kodlama.io.Universty.business.abstracts;

import kodlama.io.Universty.core.utilities.results.DataResult;
import kodlama.io.Universty.core.utilities.results.Result;
import kodlama.io.Universty.entities.concretes.Branch;
import kodlama.io.Universty.webApi.model.requests.branch.BranchAddRequest;
import kodlama.io.Universty.webApi.model.requests.branch.BranchUpdateRequest;
import kodlama.io.Universty.webApi.model.responses.branch.GetAllBranchResponse;
import kodlama.io.Universty.webApi.model.responses.branch.GetByIdBranchResponse;

import java.util.List;

public interface BranchService {

    DataResult<List<GetAllBranchResponse>> getAll();

    DataResult<GetByIdBranchResponse> getById(int id);
    Result add(BranchAddRequest branchAddRequest) throws Exception;
    Result update(int id, BranchUpdateRequest branchUpdateRequest)throws Exception;
    Result delete(int id)throws Exception;

    Branch getBranchById (int id);



}
