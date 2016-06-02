package com.kvinzanekar.kvin.showtime.Model;

import java.io.Serializable;

/**
 * Created by kvin on 6/1/16.
 */
public class ShowTime implements Serializable {


    private String ticketUrl;
    private String theaterName;
    private String dateTime;

    public String getTicketUrl() {
        return ticketUrl;
    }



    public void setTicketUrl(String ticketUrl) {
        this.ticketUrl = ticketUrl;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
