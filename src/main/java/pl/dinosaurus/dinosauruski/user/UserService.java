package pl.dinosaurus.dinosauruski.user;

import pl.dinosaurus.dinosauruski.model.User;

public interface UserService {

    boolean emailAlreadyExist(String email);

    User findByEmail(String email);

    void saveNewUser(User user);

    void update(User user);
}
