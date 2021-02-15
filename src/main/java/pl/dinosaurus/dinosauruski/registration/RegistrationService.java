package pl.dinosaurus.dinosauruski.registration;

import pl.dinosaurus.dinosauruski.model.User;


public interface RegistrationService {

    User saveUserBeforeEmailVerification(User user);

    void sendVerificationTokenByEmail(User user);

    void updateUserAfterVerification(User user);

}
