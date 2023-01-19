package kodlama.io.Universty.webApi.controllers;

import kodlama.io.Universty.business.abstracts.LessonService;
import kodlama.io.Universty.core.utilities.results.DataResult;
import kodlama.io.Universty.core.utilities.results.Result;
import kodlama.io.Universty.webApi.model.requests.lesson.LessonAddRequest;
import kodlama.io.Universty.webApi.model.requests.lesson.LessonUpdateRequest;
import kodlama.io.Universty.webApi.model.responses.lesson.GetAllLessonResponse;
import kodlama.io.Universty.webApi.model.responses.lesson.GetByIdLessonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/lessons")
public class LessonControllers {

    private LessonService lessonService;

    public LessonControllers(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/getall")
    public ResponseEntity<DataResult<List<GetAllLessonResponse>>> getAll() {
        return new ResponseEntity<>(lessonService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<GetByIdLessonResponse>> getById(@PathVariable int id)
            throws Exception {
        return new ResponseEntity<>(lessonService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody LessonAddRequest lessonAddRequest)
            throws Exception {
        return new ResponseEntity<>(lessonService.add(lessonAddRequest), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> update(
            @PathVariable int id, @RequestBody LessonUpdateRequest lessonUpdateRequest)
            throws Exception {
        return new ResponseEntity<>(lessonService.update(id, lessonUpdateRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable int id) throws Exception {
        return new ResponseEntity<>(lessonService.delete(id), HttpStatus.OK);
    }
}
