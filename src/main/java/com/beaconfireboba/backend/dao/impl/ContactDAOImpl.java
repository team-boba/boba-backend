package com.beaconfireboba.backend.dao.impl;

import com.beaconfireboba.backend.dao.AbstractHibernateDAO;
import com.beaconfireboba.backend.dao.ContactDAO;
import com.beaconfireboba.backend.entity.Contact;
import org.springframework.stereotype.Repository;

@Repository("contactDao")
public class ContactDAOImpl extends AbstractHibernateDAO<Contact> implements ContactDAO {
    public ContactDAOImpl() { setClazz(Contact.class); }

    @Override
    public Contact getContactById(int id) {
        return findById(id);
    }

    @Override
    public Contact setContact(Contact contact) {
        return save(contact);
    }
}
