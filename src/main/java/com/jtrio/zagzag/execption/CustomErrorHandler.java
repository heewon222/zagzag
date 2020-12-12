package com.jtrio.zagzag.execption;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class CustomErrorHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity handleError(ApiException e){

        return new ResponseEntity<>(e.getStatus());
    }

}
