package kino.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import kino.utils.CustomDateSerializer;

import java.util.Date;

public class TicketsPerDate {
    private Date date;
    private Integer numberOfTickets;

    public TicketsPerDate(Date date, Integer numberOfTickets) {
        this.date = date;
        this.numberOfTickets = numberOfTickets;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(Integer numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }
}
