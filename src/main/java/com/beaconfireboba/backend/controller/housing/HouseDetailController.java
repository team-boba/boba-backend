package com.beaconfireboba.backend.controller.housing;

import com.beaconfireboba.backend.domain.common.ServiceStatus;
import com.beaconfireboba.backend.domain.housing.HousingResponse;
import com.beaconfireboba.backend.entity.House;
import com.beaconfireboba.backend.service.housing.HousingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/employee")
public class HouseDetailController {
    private HousingService housingService;

    @GetMapping(value="/profile/{houseId}")
    public HousingResponse getHouse(@PathVariable String houseId){
        HousingResponse housingResponse = new HousingResponse();
        House house = this.housingService.getHouseById(Integer.parseInt(houseId));
        if (house != null){
            prepareResponse (housingResponse, true, "");
        } else {
            prepareResponse(housingResponse, false, "No house found.");
        }
        return housingResponse;
    }

    private void prepareResponse(HousingResponse response, boolean success, String errorMessage){
        response.setServiceStatus(new ServiceStatus(success ? "SUCESS" : "FAILED", success, errorMessage));
    }

    @Autowired
    public void setHousingService(HousingService housingService){
        this.housingService = housingService;
    }
}
