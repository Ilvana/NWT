package kino.model.repositories;

import kino.model.entities.Movie;
import kino.model.entities.Screening;
import kino.model.entities.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScreeningRepository extends JpaRepository<Screening, Integer>{
    List<Screening> findByMovie(Movie movie);
    List<Screening> findByTheater(Theater theater);
}
