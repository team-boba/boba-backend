package com.beaconfireboba.backend.dao.impl;

import com.beaconfireboba.backend.dao.AbstractHibernateDAO;
import com.beaconfireboba.backend.dao.PersonDAO;
import com.beaconfireboba.backend.entity.Person;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("personDao")
public class PersonDAOImpl extends AbstractHibernateDAO<Person> implements PersonDAO {
    public PersonDAOImpl() { setClazz(Person.class); }

    @Override
    public Person getPersonByPersonId(int personId) {
        return findById(personId);
    }

    @Override
    public Person getPersonByUserId(int userId) {
        Session session = getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Person> cq = cb.createQuery(Person.class);
        Root<Person> root = cq.from(Person.class);
        cq.where(cb.equal(root.get("userId"), userId));
        Person person = session.createQuery(cq).uniqueResult();
        if (person == null) return null;

        person.getContacts().size();
        person.getEmployee().getPersonalDocuments().size();
        person.getEmployee().getHouse().getFacilities().size();
        person.getEmployee().getFacilityReports().size();
        person.getEmployee().getFacilityReportDetails().size();
        return person;
    }

    @Override
    public Person setPerson(Person person) {
        return save(person);
    }
}
