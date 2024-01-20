package com.janioofi.helpdesk.exceptions;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidationErrors implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDateTime timestamp = LocalDateTime.now();
    private String error;
    private String path;
    private Integer status;
    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationErrors(){
    }

    public ValidationErrors(String error, String path, Integer status, List<FieldMessage> errors) {
        this.error = error;
        this.path = path;
        this.status = status;
        this.errors = errors;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addErrors(String fieldName, String message) {
        this.errors.add(new FieldMessage(fieldName, message));
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

}
