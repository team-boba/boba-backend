package com.beaconfireboba.backend.dao;

import com.beaconfireboba.backend.entity.Person;


public interface PersonDAO {
    Person getPersonByPersonId(int personId);

    Person getPersonByUserId(int userId);

    Person setPerson(Person person);
}