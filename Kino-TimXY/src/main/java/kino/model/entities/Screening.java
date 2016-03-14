package kino.model.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "screening")
@Entity
public class Screening {
    private static final long serialVersionUID=123456784L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "timeBegin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeBegin;

    @Column(name = "timeEnd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeEnd;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie")
    private Movie movie;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "theater")
    private Theater theater;

    public Screening() {

    }

    public Screening(Date timeBegin, Date timeEnd, Movie movie, Theater theater) {
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
        this.movie = movie;
        this.theater = theater;
    }

    public Integer getId() {
        return id;
    }

    public Date getTimeBegin() {
        return timeBegin;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public Movie getMovie() {
        return movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public void setTimeBegin(Date timeBegin) {
        this.timeBegin = timeBegin;
    }
}