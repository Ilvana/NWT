package kino.model.repositories;

import kino.model.entities.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {
    List<VerificationToken> findByToken(String token);
}
