package com.jeff.actualite.domain.response;

import java.time.Instant;

public class ErrorResponse extends AbstractResponse {
    public ErrorResponse(Integer status, String message) {
        super(status, Instant.now(), message);
    }
}
