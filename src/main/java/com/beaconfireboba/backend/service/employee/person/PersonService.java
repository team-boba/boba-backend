package com.beaconfireboba.backend.service.employee.person;

import com.beaconfireboba.backend.dao.PersonDAO;
import com.beaconfireboba.backend.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PersonService {

    private PersonDAO personDao;

    @Autowired
    public void setPersonDao(PersonDAO personDao) {
        this.personDao = personDao;
    }

    @Transactional
    public Person getPersonByUserId(Integer id) {
        return personDao.getPersonByUserId(id);
    }

    @Transactional
    public Person getPersonByPersonId(Integer id) {
        return personDao.getPersonByPersonId(id);
    }

    @Transactional
    public Person setPerson(Person person) {
        return personDao.setPerson(person);
    }
}
