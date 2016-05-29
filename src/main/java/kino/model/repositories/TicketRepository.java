package kino.model.repositories;

import kino.model.entities.Screening;
import kino.model.entities.Ticket;
import kino.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByScreening(Screening screening);
    List<Ticket> findByUser(User user);
    @Query("SELECT COUNT(t.id) FROM Ticket t JOIN t.screening s WHERE s.id = t.screening.id AND s.timeBegin BETWEEN ?1 AND ?2 GROUP BY s.id")
    public List<Long> getTicketsNumberPerDate(Date dateBegin, Date dateEnd);
}
