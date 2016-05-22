package kino.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import kino.utils.CustomDateSerializer;

import java.util.Date;

public class ScreeningsPerDate {
    private Date date;
    private Integer numberOfScreenings;

    public ScreeningsPerDate(Date date, Integer numberOfScreenings) {
        this.date = date;
        this.numberOfScreenings = numberOfScreenings;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNumberOfScreenings() {
        return numberOfScreenings;
    }

    public void setNumberOfScreenings(Integer numberOfScreenings) {
        this.numberOfScreenings = numberOfScreenings;
    }
}
