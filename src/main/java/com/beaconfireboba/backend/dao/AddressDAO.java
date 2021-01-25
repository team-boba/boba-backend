package com.beaconfireboba.backend.dao;

import com.beaconfireboba.backend.entity.Address;
import com.beaconfireboba.backend.entity.Person;

public interface AddressDAO {
    Address getAddressById(int id);

    Address setAddress(Address address);
}