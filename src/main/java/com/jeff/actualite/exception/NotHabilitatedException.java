package com.jeff.actualite.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class NotHabilitatedException extends RuntimeException {
    private String errorMessage;
}
