package com.example.mobilecaseshop.models;

import java.util.List;

public class Brand {
    private String name;
    private List<PhoneModel> models;

    public Brand(String name, List<PhoneModel> models) {
        this.name = name;
        this.models = models;
    }

    public String getName() {
        return name;
    }

    public List<PhoneModel> getModels() {
        return models;
    }

    // This is important for the dropdown (Spinner) to display the brand name correctly.
    @Override
    public String toString() {
        return this.name;
    }
}
