package com.jeff.actualite.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.BindingResult;

@Getter
@Setter
@AllArgsConstructor
public class ConstraintViolationException extends RuntimeException {
    private BindingResult bindingResult;
}
