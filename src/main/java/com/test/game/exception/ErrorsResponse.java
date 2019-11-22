package com.test.game.exception;

import java.util.List;

public class ErrorsResponse {
    private List<ErrorItem> errors;

    public List<ErrorItem> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorItem> errors) {
        this.errors = errors;
    }

    public ErrorsResponse(List<ErrorItem> errors) {
        this.errors = errors;
    }
}