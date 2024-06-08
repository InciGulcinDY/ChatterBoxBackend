package com.tobeto.ChatterBoxBackend.core.utilities.results.dataResults;

import com.tobeto.ChatterBoxBackend.core.utilities.results.DataResult;

public class ErrorDataResult<T> extends DataResult<T> {
    public ErrorDataResult(T data) {
        super(data, false);
    }

    public ErrorDataResult(T data, String message) {
        super(data, false, message);
    }

    public ErrorDataResult(String message){
        super(null, false, message);
    }

    public ErrorDataResult(){
        super(null,false);
    }

}
