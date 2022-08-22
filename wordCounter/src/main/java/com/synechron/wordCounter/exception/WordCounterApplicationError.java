package com.synechron.wordCounter.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordCounterApplicationError {

    private int code;
    private String message;
    private HttpStatus status;

}
