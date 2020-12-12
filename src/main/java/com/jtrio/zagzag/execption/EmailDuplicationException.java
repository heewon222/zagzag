package com.jtrio.zagzag.execption;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class EmailDuplicationException extends ApiException {
    public EmailDuplicationException(String msg){
        super(msg);

    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
