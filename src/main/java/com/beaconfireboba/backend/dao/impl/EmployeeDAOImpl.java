package com.beaconfireboba.backend.dao.impl;

import com.beaconfireboba.backend.dao.AbstractHibernateDAO;
import com.beaconfireboba.backend.dao.EmployeeDAO;
import com.beaconfireboba.backend.domain.hr.profile.ProfileSummaryRequest;
import com.beaconfireboba.backend.domain.hr.visaManagement.VisaManagementRequest;
import com.beaconfireboba.backend.entity.Employee;
import com.beaconfireboba.backend.entity.PersonalDocument;
import com.beaconfireboba.backend.entity.VisaStatus;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Repository("employeeDao")
public class EmployeeDAOImpl extends AbstractHibernateDAO<Employee> implements EmployeeDAO {
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

            int dayLeft = computeOPTExpireDayLeft(employee.getVisaEndDate());
            tmp.setDayLeft(dayLeft);

            employee.getPersonalDocuments().size();
            optDocAndStatus result = checkOPTDoc(employee.getPersonalDocuments(), dayLeft);
            List<PersonalDocument> personalDocuments = result.doc;
            tmp.setPersonalDocuments(personalDocuments);
            tmp.setNextStep(result.nextStep);
            tmp.setAction(result.action);
            res.add(tmp);
        }

        return res;
    }

    public static class optDocAndStatus {
        public static List<PersonalDocument> doc;
        public static String nextStep;
        public static String action;
    }

    // return all opt related doc, check the opt status
    public optDocAndStatus checkOPTDoc (List<PersonalDocument> docList, int dayLeft) {
        Map<String, Integer> optMap = new HashMap<>();
        optMap.put("OPT Receipt", 1);
        optMap.put("OPT EAD", 2);
        optMap.put("I-983", 3);
        optMap.put("Signed I-983", 4);
        optMap.put("I-20", 5);
        optMap.put("OPT STEM Receipt", 6);
        optMap.put("OPT STEM EAD", 7);

        Map<Integer, String> nextStepMap = new HashMap<>();
        nextStepMap.put(0, "OPT Receipt");
        nextStepMap.put(1, "OPT EAD");
        nextStepMap.put(2, "I-983");
        nextStepMap.put(3, "Signed I-983");
        nextStepMap.put(4, "I-20");
        nextStepMap.put(5, "OPT STEM Receipt");
        nextStepMap.put(6, "OPT STEM EAD");
        nextStepMap.put(7, "All Completed");

        List<PersonalDocument> doc = new ArrayList<>();
        int status = 0;

        for (int i = 0; i < docList.size(); i++) {
            String docName = docList.get(i).getTitle();
            if (optMap.containsKey(docName)) {
                doc.add(docList.get(i));

                if (optMap.get(docName) > status) {
                    status = optMap.get(docName);
                }
            }
        }
        optDocAndStatus res = new optDocAndStatus();
        optDocAndStatus.doc = doc;
        optDocAndStatus.nextStep = nextStepMap.get(status);
        if (status == 0 || (status == 2 && dayLeft <= 100) || status == 4 || status == 6) {
            optDocAndStatus.action = "Send Notification";
        } else if (status == 3) {
            optDocAndStatus.action = "Sign I-983";
        } else {
            optDocAndStatus.action = "nothing needed";
        }
        return res;
    }

    public int computeOPTExpireDayLeft (String visaEndDate) {
        int res = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date endDate = sdf.parse(visaEndDate);
            Date currentDate = Calendar.getInstance().getTime();

            long diffInMillies = endDate.getTime() - currentDate.getTime();
            if (diffInMillies > 0) {
                long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                res = (int) diff;
            }
        } catch (Exception e) {
            System.out.println("cannot parse visaEndDate to yyyy-MM-dd format");
        }
        return res;
    }

    @Override
    public Employee setEmployee(Employee employee) {
        return save(employee);
    }
}
