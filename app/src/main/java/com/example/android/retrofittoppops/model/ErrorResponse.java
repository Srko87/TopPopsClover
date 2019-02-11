package com.example.android.retrofittoppops.model;

public class ErrorResponse {
    private String type;
    private String message;
    private int code;

    public ErrorResponse(String type, String message, int code) {
        this.type = type;
        this.message = message;
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
