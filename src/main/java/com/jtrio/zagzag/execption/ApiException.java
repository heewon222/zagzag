package com.jtrio.zagzag.execption;

import org.springframework.http.HttpStatus;

public  abstract class ApiException extends RuntimeException{
    public ApiException(String msg) {
        super(msg);
    }
    abstract HttpStatus getStatus();

}

