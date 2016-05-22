package kino.model.repositories;

import kino.model.entities.Movie;
import kino.model.entities.Screening;
import kino.model.entities.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ScreeningRepository extends JpaRepository<Screening, Integer>{
    List<Screening> findByMovie(Movie movie);
    List<Screening> findByTheater(Theater theater);
    @Query("SELECT COUNT(s) FROM Screening s WHERE s.timeBegin BETWEEN ?1 AND ?2")
    public Integer getScreeningsNumberPerDay(Date dayBegin, Date dayEnd);
}
