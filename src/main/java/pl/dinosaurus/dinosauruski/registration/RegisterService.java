package pl.dinosaurus.dinosauruski.registration;

import pl.dinosaurus.dinosauruski.model.User;


public interface RegisterService {

    User saveUserBeforeEmailVerification(User user);

    void sendVerificationTokenByEmail(User user);

    void updateUserAfterVerification(User user);

}
