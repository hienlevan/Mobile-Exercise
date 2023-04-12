package com.example.orderapp.Model;

import com.example.orderapp.Model.ProductDetail;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDetailTrue {
    @SerializedName("success")
    private Boolean success;
    @SerializedName("message")
    private String message;
    @SerializedName("result")
    private List<ProductDetail> result;

    public ProductDetailTrue(Boolean success, String message, List<ProductDetail> result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ProductDetail> getResult() {
        return result;
    }

    public void setResult(List<ProductDetail> result) {
        this.result = result;
    }
}
