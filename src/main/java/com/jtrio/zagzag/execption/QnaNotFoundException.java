package com.jtrio.zagzag.execption;

import org.springframework.http.HttpStatus;

public class QnaNotFoundException extends ApiException{
    public QnaNotFoundException(String msg){
        super(msg);
    }

    @Override
    HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
