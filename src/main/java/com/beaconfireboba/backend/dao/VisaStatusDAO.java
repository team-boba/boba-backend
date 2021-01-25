package com.beaconfireboba.backend.dao;

import com.beaconfireboba.backend.entity.Address;
import com.beaconfireboba.backend.entity.VisaStatus;

public interface VisaStatusDAO {
    VisaStatus getVisaStatusById(int id);

    VisaStatus setVisaStatus(VisaStatus visaStatus);
}