package com.ssb.car.simulation.exceptions;


public class NotFoundException extends RuntimeException {

    final String message;

    public NotFoundException(String message) {
        super(message + " Not Found");
        this.message = message + " Not Found";
    }

}
