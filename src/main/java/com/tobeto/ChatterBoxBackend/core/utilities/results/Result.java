package com.tobeto.ChatterBoxBackend.core.utilities.results;

import lombok.Getter;

@Getter
public class Result {

    private boolean success;
    private String message;

    public Result (){}

    public Result(boolean success) {
        this.success = success;
    }

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}
