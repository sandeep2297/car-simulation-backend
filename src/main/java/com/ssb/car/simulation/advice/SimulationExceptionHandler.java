package com.ssb.car.simulation.advice;

import com.ssb.car.simulation.dto.Response;
import com.ssb.car.simulation.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class SimulationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ResponseEntity<Response> handleGenericExceptions(
            RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), request.getContextPath(), ex.getMessage(), new Date()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Response> handleNotFoundExceptions(
            RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(new Response(HttpStatus.NOT_FOUND.value(), request.getContextPath(), ex.getMessage(), new Date()),
                HttpStatus.NOT_FOUND);
    }

}
