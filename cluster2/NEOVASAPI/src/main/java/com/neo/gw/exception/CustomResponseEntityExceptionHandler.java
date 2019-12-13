package com.neo.gw.exception;

import com.neo.gw.exception.ErrorDetail;
import com.neo.gw.exception.RequestInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Calendar;
import java.util.Date;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @SuppressWarnings("unchecked")
    @ExceptionHandler(RequestInvalidException.class)
    public final ResponseEntity<ErrorDetail> handleUserNotFoundException(RequestInvalidException ex, WebRequest request) {

        ErrorDetail errorDetails = new ErrorDetail(new Date().getTime(), ex.getMessage(),
                400, "Invalid Input");
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
