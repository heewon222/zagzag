package com.jtrio.zagzag.execption;

import org.springframework.http.HttpStatus;

public class NotAllowedDeleteException extends ApiException {
    public NotAllowedDeleteException(String msg) {
        super(msg);
    }

    @Override
    HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
