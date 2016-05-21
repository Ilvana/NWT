package kino.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "event")
@Entity
public class Event implements Serializable {
    private static final long serialVersionUID=123456780L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "timeBegin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeBegin;

    @Column(name = "timeEnd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeEnd;

    public Event() {
    }

    public Event(String name, String description, Date timeBegin, Date timeEnd) {
        this.name = name;
        this.description = description;
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
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

    public Date getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(Date timeBegin) {
        this.timeBegin = timeBegin;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", timeBegin=" + timeBegin +
                ", timeEnd=" + timeEnd +
                '}';
    }
}
