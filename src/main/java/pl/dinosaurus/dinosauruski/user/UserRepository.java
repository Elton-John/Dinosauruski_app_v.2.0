package pl.dinosaurus.dinosauruski.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dinosaurus.dinosauruski.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);


}


