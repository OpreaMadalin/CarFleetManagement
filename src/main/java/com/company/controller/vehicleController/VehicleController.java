package com.company.controller.vehicleController;

import com.company.controller.databaseController.MongoController;
import com.company.model.vehicle.VehicleRequestBody;
import org.bson.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;


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
    public ArrayList<String> getVehicles() {

        MongoController mc = new MongoController();
        ArrayList<Document> cars = mc.getVehicles();
        ArrayList<String> carDetails = new ArrayList<>();
        for(Document car : cars){
            carDetails.add(car.get("brand").toString());
        }
        return carDetails;
    }
}
