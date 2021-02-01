package com.beaconfireboba.backend.util;

import com.beaconfireboba.backend.dao.impl.EmployeeDAOImpl;
import com.beaconfireboba.backend.entity.PersonalDocument;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class OptStatusUtil {
    public List<PersonalDocument> doc;
    public String nextStep;
    public String action;
    public static Map<String, Integer> optMap;
    public static Map<Integer, String> nextStepMap;

    public OptStatusUtil() {
        optMap = new HashMap<>();
        optMap.put("OPT Receipt", 1);
        optMap.put("OPT EAD", 2);
        optMap.put("I-983", 3);
        optMap.put("Signed I-983", 4);
        optMap.put("I-20", 5);
        optMap.put("OPT STEM Receipt", 6);
        optMap.put("OPT STEM EAD", 7);

        nextStepMap = new HashMap<>();
        nextStepMap.put(0, "OPT Receipt");
        nextStepMap.put(1, "OPT EAD");
        nextStepMap.put(2, "I-983");
        nextStepMap.put(3, "Signed I-983");
        nextStepMap.put(4, "I-20");
        nextStepMap.put(5, "OPT STEM Receipt");
        nextStepMap.put(6, "OPT STEM EAD");
        nextStepMap.put(7, "All Completed");
    }

    // return all opt related doc, check the opt status
    public void checkOPTDoc (List<PersonalDocument> docList, int dayLeft) {
        List<PersonalDocument> doc = new ArrayList<>();
        int status = 0;

        for (int i = 0; i < docList.size(); i++) {
            String docName = docList.get(i).getTitle();
            if (this.optMap.containsKey(docName)) {
                doc.add(docList.get(i));

                if (this.optMap.get(docName) > status) {
                    status = this.optMap.get(docName);
                }
            }
        }

        this.doc = doc;
        this.nextStep = nextStepMap.get(status);
        if (status == 0 || (status == 2 && dayLeft <= 100) || status == 4 || status == 6) {
            this.action = "Send Notification";
        } else if (status == 3) {
            this.action = "Sign I-983";
        } else {
            this.action = "nothing needed";
        }
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
}
