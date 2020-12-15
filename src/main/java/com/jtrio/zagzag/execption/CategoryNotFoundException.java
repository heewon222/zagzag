package com.jtrio.zagzag.execption;

import org.springframework.http.HttpStatus;

public class CategoryNotFoundException extends ApiException{
    public CategoryNotFoundException(String msg){
        super(msg);
    }

    @Override
    HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
