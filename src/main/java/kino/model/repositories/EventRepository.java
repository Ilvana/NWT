package kino.model.repositories;

import kino.model.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query("SELECT COUNT(e) FROM Event e WHERE e.timeBegin BETWEEN ?1 AND ?2")
    public Integer getEventsNumberPerDate(Date dateBegin, Date dateEnd);
    @Query("SELECT e FROM Event e WHERE e.timeBegin BETWEEN ?1 AND ?2")
    public List<Event> getEventsPerDate(Date dateBegin, Date dateEnd);
}
