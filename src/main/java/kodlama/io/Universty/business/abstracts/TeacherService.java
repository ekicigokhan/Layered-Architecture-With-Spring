package kodlama.io.Universty.business.abstracts;

import kodlama.io.Universty.core.utilities.results.DataResult;
import kodlama.io.Universty.core.utilities.results.Result;
import kodlama.io.Universty.webApi.model.requests.teacher.TeacherAddRequest;
import kodlama.io.Universty.webApi.model.requests.teacher.TeacherUpdateRequest;
import kodlama.io.Universty.webApi.model.responses.teacher.GetAllTeacherResponse;
import kodlama.io.Universty.webApi.model.responses.teacher.GetByIdTeacherResponse;

import java.util.List;

public interface TeacherService {

  DataResult<List<GetAllTeacherResponse>> getAll();
  DataResult<GetByIdTeacherResponse> getById(int id) throws Exception;
  Result add(TeacherAddRequest teacherAddRequest) throws Exception;
  Result update(int id, TeacherUpdateRequest teacherUpdateRequest) throws Exception;
  Result delete(int id) throws Exception;
}
