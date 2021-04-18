package com.kennyhong.stonks.response;

import lombok.Data;

@Data
public class GenericResponse {
    private String responseMessage;

    public GenericResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
