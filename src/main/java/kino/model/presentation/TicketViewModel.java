package kino.model.presentation;

import kino.model.entities.Screening;
import kino.model.entities.Ticket;
import kino.model.entities.User;

public class TicketViewModel {

    private Integer id;
    private Integer seatX;
    private Integer seatY;
    private User user;
    private Screening screening;

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

    public TicketViewModel(Ticket ticket) {
        seatX = ticket.getSeatX();
        seatY = ticket.getSeatY();
        id = ticket.getId();
        user = ticket.getUser();
        screening = ticket.getScreening();
    }

    public TicketViewModel() {
    }

    public Integer getId() {
        return id;
    }

    public Integer getSeatY() {
        return seatY;
    }

    public Integer getSeatX() {
        return seatX;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSeatX(Integer seatX) {
        this.seatX = seatX;
    }

    public void setSeatY(Integer seatY) {
        this.seatY = seatY;
    }
}
