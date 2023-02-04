package com.jeff.actualite.domain.response;


import com.jeff.actualite.utils.Constant;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
public class DtoOutput extends AbstractResponse {
    private Object data;

    public DtoOutput(Object data) {
        super(HttpStatus.OK.value(), Instant.now(), Constant.OK_MESSAGE);
        this.data = data;
    }
}
