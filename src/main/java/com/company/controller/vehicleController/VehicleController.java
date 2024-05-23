package com.company.controller.vehicleController;

import com.company.controller.databaseController.MongoController;
import com.company.model.vehicle.VehicleRequestBody;
import com.company.model.vehicle.VehicleResponseBody;
import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class VehicleController {
    //Add Vehicle
    @PostMapping(value = "vehicle/add", consumes = "application/x-www-form-urlencoded")
    public RedirectView addNew(VehicleRequestBody body) {
        MongoController mc = new MongoController();
        mc.addCar(body.getBrand(), body.getModel());
        return new RedirectView("/cars", true);
    }

    //Get Vehicle Brands
    @GetMapping("/getVehicles")
    public List<VehicleResponseBody> getVehicles() {

        MongoController mc = new MongoController();
        ArrayList<Document> cars = mc.getVehicles();
        return cars.stream()
                .map(car -> {
                    String id = car.get("_id").toString();
                    String brand = car.get("brand").toString();
                    String model = car.get("model").toString();
                    return new VehicleResponseBody(id, brand, model);
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/deleteCar/{carId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCarById(@PathVariable String carId) {
        MongoController mc = new MongoController();
        mc.deleteVehicle(carId);
    }
}
