package com.janioofi.helpdesk.resources;

import com.janioofi.helpdesk.exceptions.BusinessRuntimeException;
import com.janioofi.helpdesk.exceptions.ValidationErrors;
import com.janioofi.helpdesk.exceptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(RecordNotFoundException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(BusinessRuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBadRequestException(BusinessRuntimeException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrors validationErrors(ConstraintViolationException ex, HttpServletRequest request){
        ValidationErrors field = new ValidationErrors();
        field.setPath(request.getRequestURI());
        field.setStatus(HttpStatus.BAD_REQUEST.value());
        field.setError("Validation Error");
        for(var x : ex.getConstraintViolations()){
            field.addErrors(x.getPropertyPath().toString(), x.getMessage());

        }
        return field;
    }
}
