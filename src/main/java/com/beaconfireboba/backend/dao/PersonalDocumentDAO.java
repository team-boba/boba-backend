package com.beaconfireboba.backend.dao;

import com.beaconfireboba.backend.entity.PersonalDocument;


public interface PersonalDocumentDAO {
    PersonalDocument getPersonalDocumentById(int id);

    PersonalDocument setPersonalDocument(PersonalDocument personalDocument);
}