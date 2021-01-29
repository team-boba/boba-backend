package com.beaconfireboba.backend.controller.hr;

import com.beaconfireboba.backend.domain.common.ServiceStatus;
import com.beaconfireboba.backend.domain.housing.HouseManagementResponse;
import com.beaconfireboba.backend.entity.House;
import com.beaconfireboba.backend.service.housing.HousingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/hr")
public class HrController {
    private HousingService housingService;

//    @GetMapping(value="/profile/{houseId}")
//    public HousingResponse getHouse(@PathVariable String houseId){
//        HousingResponse housingResponse = new HousingResponse();
//        House house = this.housingService.getHouseById(Integer.parseInt(houseId));
//        if (house != null){
//            prepareResponse (housingResponse, true, "");
//        } else {
//            prepareResponse(housingResponse, false, "No house found.");
//        }
//        return housingResponse;
//    }

    @GetMapping(value="/houseManagement")
    public HouseManagementResponse getAllHouses(){
        HouseManagementResponse housingResponse = new HouseManagementResponse();
        List<House> houses = this.housingService.getAllHouses();
        System.out.println("controller");
        if (houses != null) {
            housingResponse.setHouses(houses);
            prepareResponse(housingResponse, true, "");
        } else{
            prepareResponse(housingResponse, false, "No house found.");
        }
        return housingResponse;
    }

    private void prepareResponse(HouseManagementResponse response, boolean success, String errorMessage){
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }


    @Autowired
    public void setHousingService(HousingService housingService){
        this.housingService = housingService;
    }
}
