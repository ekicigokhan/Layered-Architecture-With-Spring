package kodlama.io.Universty.business.abstracts;

import kodlama.io.Universty.core.utilities.results.DataResult;
import kodlama.io.Universty.core.utilities.results.Result;
import kodlama.io.Universty.webApi.model.requests.student.StudentAddRequest;
import kodlama.io.Universty.webApi.model.requests.student.StudentUpdateRequest;
import kodlama.io.Universty.webApi.model.responses.student.GetAllStudentResponse;
import kodlama.io.Universty.webApi.model.responses.student.GetByIdStudentResponse;

import java.util.List;

public interface StudentService {

  DataResult<List<GetAllStudentResponse>> getAll();
  DataResult<GetByIdStudentResponse> getById(int id) throws Exception;
  Result add(StudentAddRequest studentAddRequest) throws Exception;
  Result update(int id, StudentUpdateRequest studentUpdateRequest) throws Exception;
  Result delete(int id) throws Exception;
}
