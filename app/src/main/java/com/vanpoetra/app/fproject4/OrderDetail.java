package com.vanpoetra.app.fproject4;

import java.util.Map;

public class OrderDetail {

    public String orderID;
    public String userName;
    public String userPhone;
    public String seatCount;
    public String poName;
    public String busNo;
    public String cityDeparture;
    public String cityArrival;
    public String terminalDeparture;
    public String terminalArrival;
    public String dateDeparture;
    public String dateArrival;
    public String timeDeparture;
    public String timeArrival;
    public String travelTime;
    public String totalPrice;
    public String time;

    public OrderDetail(String orderID, String userName, String userPhone, String seatCount, String poName, String busNo, String cityDeparture, String cityArrival, String terminalDeparture, String terminalArrival, String dateDeparture, String dateArrival, String timeDeparture, String timeArrival, String travelTime, String totalPrice, String time) {
        this.orderID = orderID;
        this.userName = userName;
        this.userPhone = userPhone;
        this.seatCount = seatCount;
        this.poName = poName;
        this.busNo = busNo;
        this.cityDeparture = cityDeparture;
        this.cityArrival = cityArrival;
        this.terminalDeparture = terminalDeparture;
        this.terminalArrival = terminalArrival;
        this.dateDeparture = dateDeparture;
        this.dateArrival = dateArrival;
        this.timeDeparture = timeDeparture;
        this.timeArrival = timeArrival;
        this.travelTime = travelTime;
        this.totalPrice = totalPrice;
        this.time = time;
    }
}
