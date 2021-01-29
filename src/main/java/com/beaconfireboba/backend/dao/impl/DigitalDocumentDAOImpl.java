package com.beaconfireboba.backend.dao.impl;

import com.beaconfireboba.backend.dao.AbstractHibernateDAO;
import com.beaconfireboba.backend.dao.DigitalDocumentDAO;
import com.beaconfireboba.backend.entity.DigitalDocument;
import org.springframework.stereotype.Repository;

@Repository("digitalDocumentDao")
public class DigitalDocumentDAOImpl extends AbstractHibernateDAO<DigitalDocument> implements DigitalDocumentDAO {
    public DigitalDocumentDAOImpl() { setClazz(DigitalDocument.class); }

    @Override
    public DigitalDocument getDigitalDocumentById(int id) {
        return findById(id);
    }

    @Override
    public DigitalDocument setDigitalDocument(DigitalDocument digitalDocument) {
        return save(digitalDocument);
    }
}
