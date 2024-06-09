package com.tobeto.ChatterBoxBackend.core.utilities.results.authentication;

import com.tobeto.ChatterBoxBackend.core.utilities.results.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthCResult extends Result {

    private LoginResponse loginResponse;

    public AuthCResult(boolean success, String message, LoginResponse loginResponse) {
        super(success, message);
        this.loginResponse = loginResponse;
    }
}
