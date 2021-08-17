package com.company.model.vehicle;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VehicleRequestBody {

    private final String brand;
    private final String model;

    public VehicleRequestBody(@JsonProperty("brand") String brand,
                              @JsonProperty("model") String model) {
        this.brand = brand;
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }
}
