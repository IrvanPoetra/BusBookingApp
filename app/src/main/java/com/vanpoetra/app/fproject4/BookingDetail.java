package com.vanpoetra.app.fproject4;

public class BookingDetail {

    public String terminalDeparture;
    public String terminalArrival;
    public String seatCount;
    public String dateDeparture;

    public BookingDetail(String terminalDeparture, String terminalArrival, String seatCount, String dateDeparture) {
        this.terminalDeparture = terminalDeparture;
        this.terminalArrival = terminalArrival;
        this.seatCount = seatCount;
        this.dateDeparture = dateDeparture;
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

    public String getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(String seatCount) {
        this.seatCount = seatCount;
    }

    public String getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(String dateDeparture) {
        this.dateDeparture = dateDeparture;
    }
}
