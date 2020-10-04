/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopping.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shopping.backend.util.APIStatus;
import lombok.Data;

import java.io.Serializable;

/**
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class APIResponse<T> implements Serializable {

    /**
     * status & message fields have not setter. They are assigned value when
     * initial by APIStatus parameter
     */
    private final int status;
    private final String message;
    private T data;

    public APIResponse(APIStatus apiStatus, T data) {

        if (apiStatus == null) {
            throw new IllegalArgumentException("APIStatus must not be null");
        }

        this.status = apiStatus.getCode();
        this.message = apiStatus.getDescription();
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
