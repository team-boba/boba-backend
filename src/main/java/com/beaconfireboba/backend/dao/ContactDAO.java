package com.beaconfireboba.backend.dao;

import com.beaconfireboba.backend.entity.Address;
import com.beaconfireboba.backend.entity.Contact;

public interface ContactDAO {
    Contact getContactById(int id);

    Contact setContact(Contact contact);
}