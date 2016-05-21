package kino.model.repositories;

import kino.model.entities.ResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResetTokenRepository extends JpaRepository<ResetToken, Integer> {
    List<ResetToken> findByToken(String token);
}
