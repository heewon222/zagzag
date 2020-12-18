package com.jtrio.zagzag.execption;

import org.springframework.http.HttpStatus;

public class NotAllowedReviewWriteException extends ApiException{
    public NotAllowedReviewWriteException(String msg){
        super(msg);
    }

    @Override
    HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
