package com.synechron.wordCounter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {WordCounterConditionNotMetException.class })
    protected ResponseEntity<WordCounterApplicationError> handleConflict(RuntimeException ex, WebRequest request) {

        Throwable rootCause = getRealRootCause(ex);

        if (rootCause instanceof WordCounterConditionNotMetException ) {
            return new ResponseEntity<WordCounterApplicationError>(
                    new WordCounterApplicationError(400, rootCause.getMessage().toString(), HttpStatus.CONFLICT),
                    HttpStatus.NOT_FOUND);
        }
        rootCause.printStackTrace();
        return new ResponseEntity<WordCounterApplicationError>(
                new WordCounterApplicationError(1001, "Some Error Occured", HttpStatus.INTERNAL_SERVER_ERROR),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Throwable getRealRootCause(Throwable ex) {
        while (ex.getCause() != null) {
            ex = ex.getCause();
        }
        return ex;
    }
}
