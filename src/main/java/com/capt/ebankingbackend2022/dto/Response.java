package com.capt.ebankingbackend2022.dto;

public class Response<T> {
    public static final int STATUS_SUCCESS = 0;
    public static final int STATUS_FAILED = 1;
    private int status;
    private String message;
    private T data;

    public Response(int status, T data) {
        this.status = status;
        this.data = data;
    }


    public Response(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Response(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
