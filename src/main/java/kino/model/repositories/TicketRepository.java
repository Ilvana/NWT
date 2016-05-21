package kino.model.repositories;

import kino.model.entities.Screening;
import kino.model.entities.Ticket;
import kino.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByScreening(Screening screening);
    List<Ticket> findByUser(User user);
}
