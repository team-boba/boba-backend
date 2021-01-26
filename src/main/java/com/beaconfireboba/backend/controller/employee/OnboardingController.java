package com.beaconfireboba.backend.controller.employee;

import com.beaconfireboba.backend.domain.employee.person.PersonRequest;
import com.beaconfireboba.backend.domain.employee.person.PersonResponse;
import com.beaconfireboba.backend.domain.common.ServiceStatus;
import com.beaconfireboba.backend.domain.onboarding.OnboardingRequest;
import com.beaconfireboba.backend.entity.Person;
import com.beaconfireboba.backend.service.employee.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee/onboarding")
public class OnboardingController {
    private PersonService personService;

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value="/person/{id}")
    public PersonResponse getOnboardingPerson(@PathVariable String id, Model model) {
        PersonResponse personResponse = new PersonResponse();

        int userId = Integer.parseInt(id);
        Person person = personService.getPersonByUserId(userId);

        if (person == null) {
            prepareResponse(personResponse, false, "cannot get person with this user id");
        } else {
            personResponse.setPerson(person);
            prepareResponse(personResponse, true, "");
        }

        return personResponse;
    }

    @PostMapping(value="/person")
    public PersonResponse setOnboardingPerson(@RequestBody PersonRequest personRequest) {
        PersonResponse personResponse = new PersonResponse();

        Person person = personRequest.getPerson();
        personService.setPerson(person);
        personResponse.setPerson(person);
        prepareResponse(personResponse, true, "");
        return personResponse;
    }

    @PostMapping(value="/submit")
    public void submitOnBoardingRequest(@RequestBody OnboardingRequest onboardingRequest) {
        System.out.println(onboardingRequest);
    }

    private void prepareResponse(PersonResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }

}