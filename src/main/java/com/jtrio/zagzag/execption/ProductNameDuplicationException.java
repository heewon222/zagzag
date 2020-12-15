package com.jtrio.zagzag.execption;

import org.springframework.http.HttpStatus;

public class ProductNameDuplicationException extends ApiException{
    public ProductNameDuplicationException(String msg){
        super(msg);
    }

    @Override
    HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
