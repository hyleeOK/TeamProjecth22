package edu.android.teamproject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by STU on 2017-09-21.
 */

public class ShipScheduleLab {

    private static  ShipScheduleLab instance;
    ArrayList<ShipSchedule> list;

    private ShipScheduleLab() {
        list = new ArrayList<>();
        makeDummyData();
    }

    private void makeDummyData() {
        for (int i = 0; i < 5; i++) {
            Calendar cal1 = new GregorianCalendar(2017, 8, 20, 10 + i, 00);
            Date departure = cal1.getTime();
            Calendar cal2 = new GregorianCalendar(2017, 8, 20, 12 + i, 00);
            Date arrival = cal2.getTime();

            ShipSchedule schedule = new ShipSchedule("ICN", "Narita", "KE100", departure, arrival);
            list.add(schedule);
        }

        for (int i = 0; i < 5; i++) {
            Calendar cal1 = new GregorianCalendar(2017, 8, 22, 10 + i, 00);
            Date departure = cal1.getTime();
            Calendar cal2 = new GregorianCalendar(2017, 8, 22, 12 + i, 00);
            Date arrival = cal2.getTime();
            ShipSchedule schedule = new ShipSchedule("ICN", "Narita", "KE100", departure, arrival);
            list.add(schedule);
        }

        for (int i = 0; i < 5; i++) {
            Calendar cal1 = new GregorianCalendar(2017, 8, 23, 10 + i, 00);
            Date departure = cal1.getTime();
            Calendar cal2 = new GregorianCalendar(2017, 8, 23, 12 + i, 00);
            Date arrival = cal2.getTime();
            ShipSchedule schedule = new ShipSchedule("ICN", "Narita", "KE100", departure, arrival);
            list.add(schedule);
        }

        for(int i = 0 ; i < 5; i++){
            Calendar cal1 = new GregorianCalendar(2017, 8, 23, 10 + i, 00);
            Date departure = cal1.getTime();
            Calendar cal2 = new GregorianCalendar(2017, 8, 23, 12 + i, 00);
            Date arrival = cal2.getTime();
            ShipSchedule schedule = new ShipSchedule("ICN", "Narita", "KE100", departure,arrival);
            list.add(schedule);

        }

    }

    public static ShipScheduleLab getInstance(){
        if(instance == null){
            instance = new ShipScheduleLab();
        }

        return instance;
    }

    public List<ShipSchedule> getList(){
        return list;
    }

    public List<ShipSchedule> getListByDepartDate(Date departDate){
        List<ShipSchedule> result = new ArrayList<>();
        for(ShipSchedule schedule : list){
            Calendar scheduleDepartTime = Calendar.getInstance();
            scheduleDepartTime.setTime(schedule.getShipdepartTime());

            Calendar searchDepartTime = Calendar.getInstance();
            searchDepartTime.setTime(departDate);

            if(scheduleDepartTime.get(Calendar.YEAR) == searchDepartTime.get(Calendar.YEAR)
                && scheduleDepartTime.get(Calendar.DAY_OF_MONTH) == searchDepartTime.get(Calendar.DAY_OF_MONTH)
                && scheduleDepartTime.get(Calendar.MONTH) == searchDepartTime.get(Calendar.MONTH)){

                result.add(schedule);

            }

        }

        return result;

    }


}
