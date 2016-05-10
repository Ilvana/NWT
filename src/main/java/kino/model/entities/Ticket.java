package kino.model.entities;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "ticket")
@Entity
public class Ticket implements Serializable {

    private static final long serialVersionUID=123456789L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "seatX")
    private Integer seatX;

    @Column(name = "seatY")
    private Integer seatY;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "screening")
    private Screening screening;

    public Ticket() {
    }

    public Ticket(Integer seatX, Integer seatY, User user, Screening screening) {
        this.seatX = seatX;
        this.seatY = seatY;
        this.user = user;
        this.screening = screening;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeatX() {
        return seatX;
    }

    public void setSeatX(Integer seatX) {
        this.seatX = seatX;
    }

    public Integer getSeatY() {
        return seatY;
    }

    public void setSeatY(Integer seatY) {
        this.seatY = seatY;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", seatX=" + seatX +
                ", seatY=" + seatY +
                ", user=" + user +
                ", screening=" + screening +
                '}';
    }
}
