package com.beaconfireboba.backend.controller.hr;

import com.beaconfireboba.backend.domain.common.ServiceStatus;
import com.beaconfireboba.backend.domain.hr.HrEmailSendRequest;
import com.beaconfireboba.backend.domain.hr.visaManagement.VisaManagementRequest;
import com.beaconfireboba.backend.domain.hr.visaManagement.VisaManagementResponse;
import com.beaconfireboba.backend.domain.hr.visaManagement.VisaManagementUploadRequest;
import com.beaconfireboba.backend.entity.PersonalDocument;
import com.beaconfireboba.backend.entity.VisaStatus;
import com.beaconfireboba.backend.service.hr.VisaManagementService;
import com.beaconfireboba.backend.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value="/hr")
public class EmailSendingController {
    private MailUtil mailUtil;

    @Autowired
    public void setMailUtil(MailUtil mailUtil) {
        this.mailUtil = mailUtil;
    }

    @Async
    @PostMapping(value="/sendEmail")
    @ResponseBody
    public void sendEmailToEmployee(@RequestBody HrEmailSendRequest hrEmailSendRequest){
        mailUtil.sendGeneralMail(hrEmailSendRequest.getEmail(), hrEmailSendRequest.getSubject(),
                hrEmailSendRequest.getMessage());
    }
}
