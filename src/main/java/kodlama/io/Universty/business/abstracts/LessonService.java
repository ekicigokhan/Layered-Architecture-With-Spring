package kodlama.io.Universty.business.abstracts;

import kodlama.io.Universty.core.utilities.results.DataResult;
import kodlama.io.Universty.core.utilities.results.Result;
import kodlama.io.Universty.entities.concretes.Lesson;
import kodlama.io.Universty.webApi.model.requests.lesson.LessonAddRequest;
import kodlama.io.Universty.webApi.model.requests.lesson.LessonUpdateRequest;
import kodlama.io.Universty.webApi.model.responses.lesson.GetAllLessonResponse;
import kodlama.io.Universty.webApi.model.responses.lesson.GetByIdLessonResponse;

import java.util.List;

public interface LessonService {

    DataResult<List<GetAllLessonResponse>> getAll();
    DataResult<GetByIdLessonResponse> getById(int id) throws Exception;
    Result add(LessonAddRequest lessonAddRequest) throws Exception;
    Result update(int id, LessonUpdateRequest lessonUpdateRequest)throws Exception;
    Result delete(int id);
    Lesson getLessonById(int id);
    void isLessonExists(int id);

}
