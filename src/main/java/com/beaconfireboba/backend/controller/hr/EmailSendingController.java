package com.beaconfireboba.backend.controller.hr;

import com.beaconfireboba.backend.domain.hr.HrEmailSendRequest;
import com.beaconfireboba.backend.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/hr/sendEmail")
public class EmailSendingController {
    private MailUtil mailUtil;

    @Autowired
    public void setMailUtil(MailUtil mailUtil) {
        this.mailUtil = mailUtil;
    }

    @Async
    @PostMapping
    @ResponseBody
    public void sendEmailToEmployee(@RequestBody HrEmailSendRequest hrEmailSendRequest){
        mailUtil.sendGeneralMail(hrEmailSendRequest.getEmail(), hrEmailSendRequest.getSubject(),
                hrEmailSendRequest.getMessage());
    }
}
