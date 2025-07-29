package com.example.accounts.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.File;

@ResponseStatus(value= HttpStatus.NOT_FOUND)

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, String fieldName,String fieldValue){

        super("error here -"+resourceName+fieldName+fieldValue);
    }

}
