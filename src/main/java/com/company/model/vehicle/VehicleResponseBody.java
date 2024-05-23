package com.company.model.vehicle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class VehicleResponseBody {

    private String id;
    private String brand;
    private String model;
}
