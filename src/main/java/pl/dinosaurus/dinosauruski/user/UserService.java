package pl.dinosaurus.dinosauruski.user;

import pl.dinosaurus.dinosauruski.model.User;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

public interface UserService {

    boolean userAlreadyExistsByEmail(String email);

    Optional<User> findByEmail(String email);

    User saveNewUser(User user);

    void update(User user);

    UserBasicEditionDTO getUserBasicEditionDtoById(Long id);

    User findById(Long id) throws EntityNotFoundException;


}

