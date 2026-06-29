package com.amadin.ems.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DetailsAlreadyExistException extends RuntimeException {

    public DetailsAlreadyExistException(String message){
        super(message);
    }

}
