package com.example.sqlitedb;

public class Car {
    public Car(String id, String model, String color, String dpl) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.dpl = dpl;
    }

    private String id;
    private String model;
    private String color;
    private String dpl;

    public String getModel() {
        return model;
    }

    public Car(String model, String color, String dpl) {
        this.model = model;
        this.color = color;
        this.dpl = dpl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDpl() {
        return dpl;
    }

    public void setDpl(String dpl) {
        this.dpl = dpl;
    }


}
