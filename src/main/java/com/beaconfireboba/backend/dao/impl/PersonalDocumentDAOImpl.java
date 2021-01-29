package com.beaconfireboba.backend.dao.impl;

import com.beaconfireboba.backend.dao.AbstractHibernateDAO;
import com.beaconfireboba.backend.dao.PersonalDocumentDAO;
import com.beaconfireboba.backend.entity.PersonalDocument;
import org.springframework.stereotype.Repository;

@Repository("personalDocumentDao")
public class PersonalDocumentDAOImpl extends AbstractHibernateDAO<PersonalDocument> implements PersonalDocumentDAO {
    public PersonalDocumentDAOImpl() { setClazz(PersonalDocument.class); }

    @Override
    public PersonalDocument getPersonalDocumentById(int id) {
        return findById(id);
    }

    @Override
    public PersonalDocument setPersonalDocument(PersonalDocument personalDocument) {
        return save(personalDocument);
    }
}
