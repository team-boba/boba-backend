package com.beaconfireboba.backend.dao.impl;

import com.beaconfireboba.backend.dao.AbstractHibernateDAO;
import com.beaconfireboba.backend.dao.AddressDAO;
import com.beaconfireboba.backend.entity.Address;
import org.springframework.stereotype.Repository;


@Repository("addressDao")
public class AddressDAOImpl extends AbstractHibernateDAO<Address> implements AddressDAO {
    public AddressDAOImpl() { setClazz(Address.class); }

    @Override
    public Address getAddressById(int id) {
        return findById(id);
    }

    @Override
    public Address setAddress(Address address) {
        return save(address);
    }
}
