package com.tobeto.ChatterBoxBackend.core.utilities.results.results;

import com.tobeto.ChatterBoxBackend.core.utilities.results.Result;

public class ErrorResult extends Result {

    public ErrorResult() {
        super(false);
    }

    public ErrorResult(String message) {
        super(false, message);
    }

}
