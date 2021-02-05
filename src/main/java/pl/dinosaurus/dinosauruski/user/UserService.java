package pl.dinosaurus.dinosauruski.user;

import pl.dinosaurus.dinosauruski.model.User;

public interface UserService {

    User findByFirstName(String name);

    void saveNewUser(User user);

}
