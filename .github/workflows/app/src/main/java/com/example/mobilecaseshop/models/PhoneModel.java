package com.example.mobilecaseshop.models;

public class PhoneModel {
    private String modelName;

    public PhoneModel(String modelName) {
        this.modelName = modelName;
    }

    public String getModelName() {
        return modelName;
    }

    // Also needed for the dropdown (Spinner) to display the model name.
    @Override
    public String toString() {
        return this.modelName;
    }
}
