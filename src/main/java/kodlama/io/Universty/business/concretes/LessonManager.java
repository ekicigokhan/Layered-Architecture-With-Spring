package kodlama.io.Universty.business.concretes;

import kodlama.io.Universty.business.abstracts.LessonService;
import kodlama.io.Universty.business.constants.Messages;
import kodlama.io.Universty.core.utilities.customExceptions.BusinessException;
import kodlama.io.Universty.core.utilities.results.DataResult;
import kodlama.io.Universty.core.utilities.results.Result;
import kodlama.io.Universty.core.utilities.results.SuccessDataResult;
import kodlama.io.Universty.core.utilities.results.SuccessResult;
import kodlama.io.Universty.dataAccess.abstracts.LessonRepository;
import kodlama.io.Universty.entities.concretes.Lesson;
import kodlama.io.Universty.webApi.model.requests.lesson.LessonAddRequest;
import kodlama.io.Universty.webApi.model.requests.lesson.LessonUpdateRequest;
import kodlama.io.Universty.webApi.model.responses.lesson.GetAllLessonResponse;
import kodlama.io.Universty.webApi.model.responses.lesson.GetByIdLessonResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonManager implements LessonService {

    private LessonRepository lessonRepository;

    public LessonManager(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public DataResult<List<GetAllLessonResponse>> getAll() {

        List<Lesson> lessons = lessonRepository.findAll();
        List<GetAllLessonResponse> lessonResponseList = new ArrayList<>();
        for (Lesson inDbLesson : lessons) {
            GetAllLessonResponse getAllLessonResponse = buildGetAllLessonResponseFromLesson(inDbLesson);
            lessonResponseList.add(getAllLessonResponse);
        }
        return new SuccessDataResult<>(lessonResponseList, Messages.GetListMessages.LESSONS_LISTED);
    }

    @Override
    public DataResult<GetByIdLessonResponse> getById(int id) throws Exception {
        Lesson inDbLesson = lessonRepository.findById(id).orElseThrow(() -> new BusinessException(Messages.ErrorMessages.ID_NOT_FOUND));
        GetByIdLessonResponse getByIdLessonResponse = buildGetByIdLessonResponseFromLesson(inDbLesson);
        return new SuccessDataResult<>(getByIdLessonResponse, Messages.GetByIdMessages.LESSON_BROUGHT_SUCCESSFULLY);
    }

    @Override
    public Result add(LessonAddRequest lessonAddRequest) throws Exception {
        existsByLessonName(lessonAddRequest.getName());
        Lesson lesson = buildLessonAddRequestToLesson(lessonAddRequest);
        lessonRepository.save(lesson);
        return new SuccessResult(Messages.AddMessages.LESSON_ADDED + " " + lessonAddRequest.getName());
    }

    @Override
    public Result update(int id, LessonUpdateRequest lessonUpdateRequest) throws Exception {
        existsByLessonName(lessonUpdateRequest.getName());
        Lesson inDbLesson = lessonRepository.findById(id).orElseThrow(() -> new BusinessException(Messages.ErrorMessages.ID_NOT_FOUND));
        Lesson lesson = buidLessonUpdateRequestToLesson(lessonUpdateRequest);
        lesson.setId(inDbLesson.getId());
        lessonRepository.save(lesson);
        return new SuccessResult(Messages.UpdateMessages.LESSON_UPDATED + " " + lessonUpdateRequest.getName());
    }

    @Override
    public Result delete(int id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> new BusinessException(Messages.ErrorMessages.ID_NOT_FOUND));
        lessonRepository.deleteById(id);
        return new SuccessResult(Messages.DeleteMessages.LESSON_DELETED + " " + lesson.getName());
    }

    @Override
    public Lesson getLessonById(int id) {
        return lessonRepository.findById(id).orElseThrow(() -> new BusinessException(Messages.ErrorMessages.ID_NOT_FOUND));
    }

    @Override
    public void isLessonExists(int id) {
        if (!lessonRepository.existsById(id)) {
            throw new BusinessException(Messages.ErrorMessages.ID_NOT_FOUND);
        }
    }

    public void existsByLessonName(String name) {
        if (lessonRepository.existsByName(name)) {
            throw new BusinessException(Messages.ErrorMessages.LESSON_NAME_DUPLICATED);
        }
    }

    private static GetAllLessonResponse buildGetAllLessonResponseFromLesson(Lesson inDbLesson) {
        return GetAllLessonResponse.builder()
                .id(inDbLesson.getId())
                .name(inDbLesson.getName())
                .build();
    }

    private static GetByIdLessonResponse buildGetByIdLessonResponseFromLesson(Lesson inDbLesson) {
        return GetByIdLessonResponse.builder()
                .name(inDbLesson.getName())
                .description(inDbLesson.getDescription())
                .students(inDbLesson.getStudents())
                .build();
    }

    private static Lesson buildLessonAddRequestToLesson(LessonAddRequest lessonAddRequest) {
        return Lesson.builder()
                .name(lessonAddRequest.getName())
                .description(lessonAddRequest.getDescription())
                .build();
    }

    private static Lesson buidLessonUpdateRequestToLesson(LessonUpdateRequest lessonUpdateRequest) {
        return Lesson.builder().name(lessonUpdateRequest.getName()).description(lessonUpdateRequest.getDescription()).build();
    }
}
