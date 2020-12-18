package com.jtrio.zagzag.execption;

import org.springframework.http.HttpStatus;

public class ProductQuantityZeroException extends ApiException{
    public ProductQuantityZeroException(String msg){
        super(msg);
    }

    @Override
    HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
