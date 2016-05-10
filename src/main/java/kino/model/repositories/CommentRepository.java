package kino.model.repositories;

import kino.model.entities.Comment;
import kino.model.entities.Event;
import kino.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
    List<Comment> findByUser(User user);
    List<Comment> findByEvent(Event event);
}
