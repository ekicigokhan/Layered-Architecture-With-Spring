package kodlama.io.Universty.business.abstracts;

import kodlama.io.Universty.core.utilities.results.DataResult;
import kodlama.io.Universty.core.utilities.results.Result;
import kodlama.io.Universty.entities.concretes.Department;
import kodlama.io.Universty.webApi.model.requests.department.DepartmentAddRequest;
import kodlama.io.Universty.webApi.model.requests.department.DepartmentUpdateRequest;
import kodlama.io.Universty.webApi.model.responses.department.GetAllDepartmentResponse;
import kodlama.io.Universty.webApi.model.responses.department.GetByIdDepartmentResponse;

import java.util.List;

public interface DepartmentService {

    DataResult<List<GetAllDepartmentResponse>> getAll();
    DataResult<GetByIdDepartmentResponse> getById(int id)throws Exception;
    Result add(DepartmentAddRequest departmentAddRequest)throws Exception;
    Result update(int id, DepartmentUpdateRequest departmentUpdateRequest)throws Exception;
    Result delete(int id)throws Exception;
    Department getDepartmentById(int id);
    void isDepartmentExists(int id);
}
