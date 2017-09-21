package edu.android.teamproject;

import android.provider.ContactsContract;

import java.util.Date;

/**
 * Created by STU on 2017-09-21.
 */

public class ShipSchedule {
    private String ShipdepartCity; // 배 이름
    private String ShipCity; // 배의 출발날짜
    private String flightNo;
    private Date ShipdepartTime; // 배의 시간 출발날짜
    private Date ShipTime; // 배의 시간 도착날짜

    public ShipSchedule(String shipdepartCity, String shipCity, String flightNo, Date shipdepartTime, Date shipTime) {
        this.ShipdepartCity = shipdepartCity;
        this.ShipCity = shipCity;
        this.flightNo = flightNo;
        this.ShipdepartTime = shipdepartTime;
        this.ShipTime = shipTime;
    }

    public String getShipdepartCity() {
        return ShipdepartCity;
    }

    public void setShipdepartCity(String shipdepartCity) {
        ShipdepartCity = shipdepartCity;
    }

    public String getShipCity() {
        return ShipCity;
    }

    public void setShipCity(String shipCity) {
        ShipCity = shipCity;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public Date getShipdepartTime() {
        return ShipdepartTime;
    }

    public void setShipdepartTime(Date shipdepartTime) {
        ShipdepartTime = shipdepartTime;
    }

    public Date getShipTime() {
        return ShipTime;
    }

    public void setShipTime(Date shipTime) {
        ShipTime = shipTime;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("출발지: ").append(ShipdepartCity).append("\n")
                .append("도착지: ").append(ShipCity).append("\n")
                .append("출발시간 :").append(ShipdepartTime.toString()).append("\n")
                .append("도착시간 : ").append(ShipTime.toString());

        return buffer.toString();

        }




}
