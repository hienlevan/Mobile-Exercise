package com.example.orderapp.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LastProduct implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("strMeal")
    private String strMeal;
    @SerializedName("strMealThumb")
    private String strMealThumb;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public int getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(int idMeal) {
        this.idMeal = idMeal;
    }

    public int getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }

    @SerializedName("idMeal")
    private int idMeal;
    @SerializedName("idcategory")
    private int idcategory;
}
