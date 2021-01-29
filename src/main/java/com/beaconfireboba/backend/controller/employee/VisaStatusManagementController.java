package com.beaconfireboba.backend.controller.employee;

import com.beaconfireboba.backend.domain.common.ServiceStatus;

import com.beaconfireboba.backend.domain.ducoment.UploadDocumentRequest;
import com.beaconfireboba.backend.domain.ducoment.UploadDocumentResponse;

import com.beaconfireboba.backend.service.employee.VisaStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/vsm")
public class VisaStatusManagementController {
    private VisaStatusService visaStatusService;

    @PostMapping(value="/uploadDocument")
    private UploadDocumentResponse addDocumentReord(@RequestBody UploadDocumentRequest uploadDocumentRequest) {
        UploadDocumentResponse uploadDocumentResponse = new UploadDocumentResponse();
        UploadDocumentRequest documentRequest = visaStatusService.adduploadPersonalDocument(uploadDocumentRequest);

        if(documentRequest != null){
            uploadDocumentResponse.setDocumentRequest(documentRequest);
            prepareResponse(uploadDocumentResponse, true, "");
        }
        else{
            prepareResponse(uploadDocumentResponse, false, "Couldn't add upload document to database");
        }

        return uploadDocumentResponse;

    }
    @Autowired
    public void setVisaStatusService(VisaStatusService visaStatusService){this.visaStatusService = visaStatusService;}


    private void prepareResponse(UploadDocumentResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }
}

