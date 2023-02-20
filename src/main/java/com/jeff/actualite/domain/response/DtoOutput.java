package com.jeff.actualite.domain.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jeff.actualite.utils.Constant;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DtoOutput extends AbstractResponse {

    private Object data;

    public DtoOutput(Object data) {
        super(HttpStatus.OK.value(), Instant.now(), Constant.OK_MESSAGE);
        this.data = data;
    }

    public DtoOutput() {
        super(HttpStatus.OK.value(), Instant.now(), Constant.OK_MESSAGE);
    }
}
