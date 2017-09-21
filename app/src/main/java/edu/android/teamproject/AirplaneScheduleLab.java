package edu.android.teamproject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by STU on 2017-09-20.
 */

public class AirplaneScheduleLab {
    private static AirplaneScheduleLab instance;
    private List<AirplaneSchedule> list;

    private AirplaneScheduleLab() {
        list = new ArrayList<>();
        makeDummyData();
    }

    private void makeDummyData() {
        for (int i = 0; i < 5; i++) {
            Calendar cal1 = new GregorianCalendar(2017, 8, 20, 10 + i, 00);
            Date departure = cal1.getTime();
            Calendar cal2 = new GregorianCalendar(2017, 8, 20, 12 + i, 00);
            Date arrival = cal2.getTime();

            AirplaneSchedule schedule = new AirplaneSchedule("ICN", "Narita", "KE100", departure, arrival);
            list.add(schedule);
        }

        for (int i = 0; i < 5; i++) {
            Calendar cal1 = new GregorianCalendar(2017, 8, 22, 10 + i, 00);
            Date departure = cal1.getTime();
            Calendar cal2 = new GregorianCalendar(2017, 8, 22, 12 + i, 00);
            Date arrival = cal2.getTime();
            AirplaneSchedule schedule = new AirplaneSchedule("ICN", "Narita", "KE100", departure, arrival);
            list.add(schedule);
        }

        for (int i = 0; i < 5; i++) {
            Calendar cal1 = new GregorianCalendar(2017, 8, 23, 10 + i, 00);
            Date departure = cal1.getTime();
            Calendar cal2 = new GregorianCalendar(2017, 8, 23, 12 + i, 00);
            Date arrival = cal2.getTime();
            AirplaneSchedule schedule = new AirplaneSchedule("ICN", "Narita", "KE100", departure, arrival);
            list.add(schedule);
        }

        for (int i = 0; i < 5; i++) {
            Calendar cal1 = new GregorianCalendar(2017, 8, 25, 10 + i, 00);
            Date departure = cal1.getTime();
            Calendar cal2 = new GregorianCalendar(2017, 8, 25, 12 + i, 00);
            Date arrival = cal2.getTime();
            AirplaneSchedule schedule = new AirplaneSchedule("ICN", "Narita", "KE100", departure, arrival);
            list.add(schedule);

        }

    }

    public static AirplaneScheduleLab getInstance() {
        if (instance == null) {
            instance = new AirplaneScheduleLab();
        }

        return instance;
    }

    public List<AirplaneSchedule> getList() {
        return list;
    }

    public List<AirplaneSchedule> getListByDepartDate(Date departDate) {
        List<AirplaneSchedule> result = new ArrayList<>();

        for (AirplaneSchedule schedule : list) {
            Calendar scheduleDepartTime = Calendar.getInstance();
            scheduleDepartTime.setTime(schedule.getDepartTime());

            Calendar searchDepartTime = Calendar.getInstance();
            searchDepartTime.setTime(departDate);

            if (scheduleDepartTime.get(Calendar.YEAR) == searchDepartTime.get(Calendar.YEAR)
                    && scheduleDepartTime.get(Calendar.MONTH) == searchDepartTime.get(Calendar.MONTH)
                    && scheduleDepartTime.get(Calendar.DAY_OF_MONTH) == searchDepartTime.get(Calendar.DAY_OF_MONTH)) {

                result.add(schedule);
            }
        }

        return result;
    }
}
