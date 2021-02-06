package pl.dinosaurus.dinosauruski.user;

import pl.dinosaurus.dinosauruski.model.User;

public interface UserService {

    User findByEmail(String email);

    void saveNewUser(User user);


}
