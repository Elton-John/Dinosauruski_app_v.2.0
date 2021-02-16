package pl.dinosaurus.dinosauruski.registration;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dinosaurus.dinosauruski.model.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

}