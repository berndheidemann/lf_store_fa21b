package com.example.lf_store_fa21b.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CurrencycodeNotFoundException extends RuntimeException {

    public CurrencycodeNotFoundException() {
        super("Currcencycode don’t exist!");
    }
}

