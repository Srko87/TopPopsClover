package com.example.android.retrofittoppops.commons.baseClasses;

import com.example.android.retrofittoppops.model.ErrorResponse;
import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    @SerializedName("error")
    private ErrorResponse errorResponse;

    public BaseResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
}
