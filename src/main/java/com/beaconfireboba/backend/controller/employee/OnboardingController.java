package com.beaconfireboba.backend.controller.employee;

import com.beaconfireboba.backend.domain.employee.person.PersonRequest;
import com.beaconfireboba.backend.domain.employee.person.PersonResponse;
import com.beaconfireboba.backend.domain.common.ServiceStatus;
import com.beaconfireboba.backend.domain.onboarding.OnboardingRequest;
import com.beaconfireboba.backend.entity.Person;
import com.beaconfireboba.backend.service.employee.OnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee/onboarding")
public class OnboardingController {
    private OnboardingService onboardingService;

    @Autowired
    public void setOnboardingService(OnboardingService onboardingService) {
        this.onboardingService = onboardingService;
    }

    @PostMapping(value="/submit")
    public void submitOnBoardingRequest(@RequestBody OnboardingRequest onboardingRequest) {
        System.out.println(onboardingRequest);
        onboardingService.saveOnboarding(onboardingRequest.getPersonRequest(), onboardingRequest.getAddressRequest(), onboardingRequest.getEmployeeRequest());
    }

//    @GetMapping(value="/person/{id}")
//    public PersonResponse getOnboardingPerson(@PathVariable String id, Model model) {
//        PersonResponse personResponse = new PersonResponse();
//
//        int userId = Integer.parseInt(id);
//        Person person = onboardingService.getPersonByUserId(userId);
//
//        if (person == null) {
//            prepareResponse(personResponse, false, "cannot get person with this user id");
//        } else {
//            personResponse.setPerson(person);
//            prepareResponse(personResponse, true, "");
//        }
//
//        return personResponse;
//    }
//
//    @PostMapping(value="/person")
//    public PersonResponse setOnboardingPerson(@RequestBody PersonRequest personRequest) {
//        PersonResponse personResponse = new PersonResponse();
//
//        Person person = personRequest.getPerson();
//        onboardingService.setPerson(person);
//        personResponse.setPerson(person);
//        prepareResponse(personResponse, true, "");
//        return personResponse;
//    }
//
//    private void prepareResponse(PersonResponse response, boolean success, String errorMessage) {
//        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
//    }

}