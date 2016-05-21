package kino.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "comment")
@Entity
public class Comment implements Serializable {
    private static final long serialVersionUID=123456781L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "event")
    private Event event;

    public Comment() {
    }

    public Comment(Date time, String content, User user, Event event) {
        this.time = time;
        this.content = content;
        this.user = user;
        this.event = event;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", time=" + time +
                ", content='" + content + '\'' +
                ", user=" + user +
                ", event=" + event +
                '}';
    }
}
