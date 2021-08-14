package com.example.android3lesson1.models;

public class Model {
    public Model(String title, String description, int image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    private String title;
    private String description;
    private int image;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
