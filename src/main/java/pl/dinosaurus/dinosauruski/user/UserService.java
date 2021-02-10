package pl.dinosaurus.dinosauruski.user;

import pl.dinosaurus.dinosauruski.model.User;

import javax.persistence.EntityNotFoundException;

public interface UserService {

    boolean emailAlreadyExist(String email);

    User findByEmail(String email);

    void saveNewUser(User user);

    void update(User user);

    UserBasicEditionDTO getUserBasicEditionDtoById(Long teacherId);

    User findById(Long teacherId) throws EntityNotFoundException;
}
