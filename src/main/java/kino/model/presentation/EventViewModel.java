package kino.model.presentation;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import kino.utils.CustomDateSerializer;
import kino.model.entities.Event;

import java.util.Date;

public class EventViewModel {

    private Integer id;
    private String name;
    private String description;
    private Date timeBegin;
    private Date timeEnd;

    public EventViewModel() {
    }

    public EventViewModel(Event event) {
        id = event.getId();
        name = event.getName();
        description = event.getDescription();
        timeBegin = event.getTimeBegin();
        timeEnd = event.getTimeEnd();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(Date timeBegin) {
        this.timeBegin = timeBegin;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }
}
