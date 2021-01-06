package com.jtrio.zagzag.execption;

import org.springframework.http.HttpStatus;

public class OrderProductNotFoundException extends ApiException{
    public OrderProductNotFoundException(String msg){
        super(msg);
    }

    @Override
    HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
