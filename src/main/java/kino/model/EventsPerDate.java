package kino.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import kino.utils.CustomDateSerializer;

import java.util.Date;

public class EventsPerDate {

    private Date date;
    private Integer numberOfEvents;

    public EventsPerDate(Date date, Integer numberOfevents) {
        this.date = date;
        this.numberOfEvents = numberOfevents;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNumberOfEvents() {
        return numberOfEvents;
    }

    public void setNumberOfEvents(Integer numberOfEvents) {
        this.numberOfEvents = numberOfEvents;
    }
}
