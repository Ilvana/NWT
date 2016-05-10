package kino.model.validation;


import kino.model.entities.Ticket;

public class TicketValidator {

    public static boolean isInvalidTicket(Ticket ticket) {
        if(ticket.getSeatX() == null || ticket.getSeatX() <= 0) {
            return true;
        }
        if(ticket.getSeatY() == null || ticket.getSeatY() <= 0) {
            return true;
        }
        if(ticket.getUser() == null) {
            return true;
        }
        if(ticket.getScreening() == null) {
            return true;
        }
        else {
            return false;
        }
    }
}
