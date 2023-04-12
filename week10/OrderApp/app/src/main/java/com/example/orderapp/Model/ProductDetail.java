package com.example.orderapp.Model;

import com.google.gson.annotations.SerializedName;

public class ProductDetail {
    @SerializedName("id")
    private int id;
    @SerializedName("meal")
    private String meal;
    @SerializedName("area")
    private String area;
    @SerializedName("category")
    private String category;
    @SerializedName("instructions")
    private String instructions;
    @SerializedName("strmealthumb")
    private String strmealthumb;
    @SerializedName("price")
    private Float price;

    public ProductDetail(int id, String meal, String area, String category, String instructions, String strmealthumb, Float price) {
        this.id = id;
        this.meal = meal;
        this.area = area;
        this.category = category;
        this.instructions = instructions;
        this.strmealthumb = strmealthumb;
        this.price = price;
    }

    public int getSucces() {
        return id;
    }

    public void setSucces(int id) {
        this.id = id;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getStrmealthumb() {
        return strmealthumb;
    }

    public void setStrmealthumb(String strmealthumb) {
        this.strmealthumb = strmealthumb;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
