package com.building.management.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BuildingAlreadyExistException extends RuntimeException {

    public BuildingAlreadyExistException(String message) {
        super(message);
    }
}
