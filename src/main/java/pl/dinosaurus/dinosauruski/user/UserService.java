package pl.dinosaurus.dinosauruski.user;

import pl.dinosaurus.dinosauruski.model.User;

public interface UserService {

    User findByUsername(String name);

    void saveNewUser(User user);

}
