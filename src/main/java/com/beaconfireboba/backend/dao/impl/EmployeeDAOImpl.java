package com.beaconfireboba.backend.dao.impl;

import com.beaconfireboba.backend.dao.AbstractHibernateDAO;
import com.beaconfireboba.backend.dao.EmployeeDAO;
import com.beaconfireboba.backend.domain.hr.profile.ProfileSummaryRequest;
import com.beaconfireboba.backend.domain.hr.visaManagement.VisaManagementRequest;
import com.beaconfireboba.backend.entity.Employee;
import com.beaconfireboba.backend.entity.PersonalDocument;
import com.beaconfireboba.backend.entity.VisaStatus;
import com.beaconfireboba.backend.util.DateUtil;
import com.beaconfireboba.backend.util.OptStatusUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Repository("employeeDao")
public class EmployeeDAOImpl extends AbstractHibernateDAO<Employee> implements EmployeeDAO {
    private OptStatusUtil optStatusUtil;

    @Autowired
    public void setOptStatusUtil(OptStatusUtil optStatusUtil) {
        this.optStatusUtil = optStatusUtil;
    }

    public EmployeeDAOImpl() { setClazz(Employee.class); }

    @Override
    public Employee getEmployeeById(int id) {
        return findById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return findAll();
    }

    @Override
    public List<ProfileSummaryRequest> getAllEmployeeWithPersonAndVisa() {
        Session session = getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);
        List<Employee> employeeList = session.createQuery(cq).getResultList();

        if (employeeList == null) return null;

        List<ProfileSummaryRequest> res = new ArrayList<>();
        for (int i = 0; i < employeeList.size(); i++) {
            ProfileSummaryRequest tmp = new ProfileSummaryRequest();
            tmp.setPersonId(employeeList.get(i).getPerson().getId());
            tmp.setFirstName(employeeList.get(i).getPerson().getFirstName());
            tmp.setLastName(employeeList.get(i).getPerson().getLastName());
            tmp.setMiddleName(employeeList.get(i).getPerson().getMiddleName());
            tmp.setSsn(employeeList.get(i).getPerson().getSSN());
            tmp.setUserId(employeeList.get(i).getPerson().getUserId());

            tmp.setStartDate(employeeList.get(i).getStartDate());
            tmp.setEmployeeId(employeeList.get(i).getId());

            tmp.setVisaType(employeeList.get(i).getVisaStatus().getVisaType());

            res.add(tmp);
        }

        return res;
    }

    @Override
    public List<VisaManagementRequest> getAllOPTEmployeeWithPersonalDocument(VisaStatus visaStatus){
        Session session = getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);
        cq.where(cb.equal(root.get("visaStatus"), visaStatus));
        List<Employee> employeeList = session.createQuery(cq).getResultList();
        if (employeeList == null) return null;

        List<VisaManagementRequest> res = new ArrayList<>();
        for (int i = 0; i < employeeList.size(); i++) {
            VisaManagementRequest tmp = new VisaManagementRequest();
            Employee employee = employeeList.get(i);
            tmp.setEmployeeId(employee.getId());
            tmp.setVisaStartDate(employee.getVisaStartDate());
            tmp.setVisaEndDate(employee.getVisaEndDate());

            tmp.setFirstName(employee.getPerson().getFirstName());
            tmp.setLastName(employee.getPerson().getLastName());
            tmp.setMiddleName(employee.getPerson().getMiddleName());
            tmp.setUserId(employee.getPerson().getUserId());
            tmp.setEmail(employee.getPerson().getEmail());

            int dayLeft = optStatusUtil.computeOPTExpireDayLeft(employee.getVisaEndDate());
            tmp.setDayLeft(dayLeft);

            employee.getPersonalDocuments().size();
            optStatusUtil.checkOPTDoc(employee.getPersonalDocuments(), dayLeft);
            List<PersonalDocument> personalDocuments = optStatusUtil.doc;
            tmp.setPersonalDocuments(personalDocuments);
            tmp.setNextStep(optStatusUtil.nextStep);
            tmp.setAction(optStatusUtil.action);
            res.add(tmp);
        }

        return res;
    }

    @Override
    public Employee setEmployee(Employee employee) {
        return save(employee);
    }
}
