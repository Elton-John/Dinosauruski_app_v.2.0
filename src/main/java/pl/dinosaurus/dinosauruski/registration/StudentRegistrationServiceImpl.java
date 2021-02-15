package pl.dinosaurus.dinosauruski.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dinosaurus.dinosauruski.model.User;

@Service
@Transactional
@AllArgsConstructor
public class StudentRegistrationServiceImpl implements RegistrationService {

    @Override
    public User saveUserBeforeEmailVerification(User user) {
        return null;
    }

    @Override
    public void sendVerificationTokenByEmail(User user) {

    }

    @Override
    public void updateUserAfterVerification(User user) {

    }
}
