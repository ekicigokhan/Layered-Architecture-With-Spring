package kodlama.io.Universty.core.exceptionHandler;

import kodlama.io.Universty.core.utilities.customExceptions.BusinessException;
import kodlama.io.Universty.core.utilities.results.ErrorResult;
import kodlama.io.Universty.core.utilities.results.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity<Result> handleBussinessException(BusinessException businessException){
    ErrorResult errorResult = new ErrorResult(businessException.getLocalizedMessage());

        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }
}
