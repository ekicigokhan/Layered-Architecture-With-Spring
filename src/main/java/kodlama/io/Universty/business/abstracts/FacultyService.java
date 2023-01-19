package kodlama.io.Universty.business.abstracts;

import kodlama.io.Universty.core.utilities.results.DataResult;
import kodlama.io.Universty.core.utilities.results.Result;
import kodlama.io.Universty.entities.concretes.Faculty;
import kodlama.io.Universty.entities.concretes.Lesson;
import kodlama.io.Universty.webApi.model.requests.faculty.FacultyAddRequest;
import kodlama.io.Universty.webApi.model.requests.faculty.FacultyUpdateRequest;
import kodlama.io.Universty.webApi.model.requests.lesson.LessonAddRequest;
import kodlama.io.Universty.webApi.model.requests.lesson.LessonUpdateRequest;
import kodlama.io.Universty.webApi.model.responses.faculty.GetAllFacultyResponse;
import kodlama.io.Universty.webApi.model.responses.faculty.GetByIdFacultyResponse;
import kodlama.io.Universty.webApi.model.responses.lesson.GetAllLessonResponse;
import kodlama.io.Universty.webApi.model.responses.lesson.GetByIdLessonResponse;

import java.util.List;

public interface FacultyService {

    DataResult<List<GetAllFacultyResponse>> getAll();
    DataResult<GetByIdFacultyResponse> getById(int id) throws Exception;
    Result add(FacultyAddRequest facultyAddRequest) throws Exception;
    Result update(int id, FacultyUpdateRequest facultyUpdateRequest)throws Exception;
    Result delete(int id)throws Exception;
    Faculty getFacultyById(int id);
    void isFacultyExists(int id);
}
