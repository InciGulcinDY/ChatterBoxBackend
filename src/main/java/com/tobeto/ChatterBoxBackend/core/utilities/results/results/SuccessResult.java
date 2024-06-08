package com.tobeto.ChatterBoxBackend.core.utilities.results.results;

import com.tobeto.ChatterBoxBackend.core.utilities.results.Result;

public class SuccessResult extends Result {

    public SuccessResult() {
        super(true);
    }

    public SuccessResult(String message) {
        super(true, message);
    }

}
