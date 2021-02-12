package pl.dinosaurus.dinosauruski.user;

import pl.dinosaurus.dinosauruski.model.Slot;
import pl.dinosaurus.dinosauruski.model.User;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface UserService {

    boolean emailAlreadyExist(String email);

    User findByEmail(String email);

    void saveNewUser(User user);

    void update(User user);

    UserBasicEditionDTO getUserBasicEditionDtoById(Long id);

    User findById(Long id) throws EntityNotFoundException;

}
