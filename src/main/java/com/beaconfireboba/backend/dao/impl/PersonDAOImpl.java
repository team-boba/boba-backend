package com.beaconfireboba.backend.dao.impl;

import com.beaconfireboba.backend.dao.AbstractHibernateDAO;
import com.beaconfireboba.backend.dao.PersonDAO;
import com.beaconfireboba.backend.entity.Person;
import org.hibernate.Session;
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

        List<Person> res = session.createQuery(cq).getResultList();
        return (res == null || res.size() != 1) ? null : res.get(0);
    }

    @Override
    public Person setPerson(Person person) {
        return save(person);
    }
}