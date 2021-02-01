package com.beaconfireboba.backend.controller.hr;

import com.beaconfireboba.backend.domain.common.ServiceStatus;
import com.beaconfireboba.backend.domain.housing.HouseManagementRequest;
import com.beaconfireboba.backend.domain.housing.HouseManagementResponse;
import com.beaconfireboba.backend.service.housing.HouseManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value="/hr/houseManagement")
public class HouseManagementController {
    private HouseManagementService houseManagementService;

    @GetMapping
    public HouseManagementResponse getAllHouses(HttpServletRequest httpServletRequest){
        HouseManagementResponse houseManagementResponse = new HouseManagementResponse();
        List<HouseManagementRequest> houseManagementRequests = this.houseManagementService.getAllHouses();
        if (houseManagementRequests != null) {
            houseManagementResponse.setHouseManagementRequests(houseManagementRequests);
            prepareResponse(houseManagementResponse, true, "");
        } else{
            prepareResponse(houseManagementResponse, false, "No house found.");
        }
        return houseManagementResponse;
    }

    private void prepareResponse(HouseManagementResponse response, boolean success, String errorMessage){
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }


    @Autowired
    public void setHouseManagementServiceService(HouseManagementService houseManagementService){
        this.houseManagementService = houseManagementService;
    }
}
