package com.example.orderapp.Model;

public class UploadImage {
    private int id;

    private String images;

    public UploadImage(int id, String images) {
        this.id = id;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
