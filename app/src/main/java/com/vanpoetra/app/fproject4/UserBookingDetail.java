package com.vanpoetra.app.fproject4;

public class UserBookingDetail {
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
    public String price;

    public UserBookingDetail(String userName, String userPhone, String seatCount, String poName, String busNo, String cityDeparture, String cityArrival, String terminalDeparture, String terminalArrival, String dateDeparture, String dateArrival, String timeDeparture, String timeArrival, String price) {
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
        this.price = price;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(String seatCount) {
        this.seatCount = seatCount;
    }

    public String getPoName() {
        return poName;
    }

    public void setPoName(String poName) {
        this.poName = poName;
    }

    public String getBusNo() {
        return busNo;
    }

    public void setBusNo(String busNo) {
        this.busNo = busNo;
    }

    public String getCityDeparture() {
        return cityDeparture;
    }

    public void setCityDeparture(String cityDeparture) {
        this.cityDeparture = cityDeparture;
    }

    public String getCityArrival() {
        return cityArrival;
    }

    public void setCityArrival(String cityArrival) {
        this.cityArrival = cityArrival;
    }

    public String getTerminalDeparture() {
        return terminalDeparture;
    }

    public void setTerminalDeparture(String terminalDeparture) {
        this.terminalDeparture = terminalDeparture;
    }

    public String getTerminalArrival() {
        return terminalArrival;
    }

    public void setTerminalArrival(String terminalArrival) {
        this.terminalArrival = terminalArrival;
    }

    public String getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(String dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    public String getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(String dateArrival) {
        this.dateArrival = dateArrival;
    }

    public String getTimeDeparture() {
        return timeDeparture;
    }

    public void setTimeDeparture(String timeDeparture) {
        this.timeDeparture = timeDeparture;
    }

    public String getTimeArrival() {
        return timeArrival;
    }

    public void setTimeArrival(String timeArrival) {
        this.timeArrival = timeArrival;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
