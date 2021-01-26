package com.beaconfireboba.backend.dao;

import com.beaconfireboba.backend.entity.DigitalDocument;

public interface DigitalDocumentDAO {
    DigitalDocument getDigitalDocumentById(int id);

    DigitalDocument setDigitalDocument(DigitalDocument digitalDocument);
}