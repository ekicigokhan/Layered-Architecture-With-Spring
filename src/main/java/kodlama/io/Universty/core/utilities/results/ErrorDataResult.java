package kodlama.io.Universty.core.utilities.results;

import kodlama.io.Universty.webApi.model.responses.teacher.GetAllTeacherResponse;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ErrorDataResult<T> extends DataResult {

    public ErrorDataResult(T data, String message) {
        super(data, false, message);
    }

    public ErrorDataResult(T data) {
        super(data, false);
    }

    public ErrorDataResult(String message) {
        super(null, false, message);
    }

    public ErrorDataResult() {
        super(null, false);
    }

}
