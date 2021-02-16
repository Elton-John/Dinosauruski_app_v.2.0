package pl.dinosaurus.dinosauruski.registration.student;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dinosaurus.dinosauruski.email.EmailService;
import pl.dinosaurus.dinosauruski.model.User;
import pl.dinosaurus.dinosauruski.registration.RegisterService;
import pl.dinosaurus.dinosauruski.model.VerificationToken;
import pl.dinosaurus.dinosauruski.registration.VerificationTokenService;
import pl.dinosaurus.dinosauruski.student.StudentService;

@Service
@Transactional
@AllArgsConstructor
public class StudentRegisterServiceImpl implements RegisterService {

    private final StudentService studentService;
    private final VerificationTokenService tokenService;
    private final EmailService emailService;

    @Override
    public User saveUserBeforeEmailVerification(User user) {
        User saved = studentService.saveNewUser(user);
        sendVerificationTokenByEmail(saved);
        return saved;
    }

    @Override
    public void sendVerificationTokenByEmail(User user) {
        VerificationToken token = tokenService.createVerificationToken(user);
        String confirmationUrl = "/register/student/confirm?token=" + token.getToken();
        String message = "Cześć! " +"\r\n"
               + "Zapraszam na Dinosaurus! " +   "\r\n"
                + "Utworzyłam dla Ciebie konto. Zalogujesz się używając swojego adresu e-mail" + "\r\n"
                + "Kliknij w poniższy link i ustaw swoje hasło" + "\r\n"
                + "http://localhost:8080" + confirmationUrl + "\r\n" +"\r\n"
                + " Z poważaniem" + "\r\n"
                + " Olga Kryukova i dinozawry.";
        emailService.send(user.getEmail(), "Twoje konto na DinosauRUS już gotowe!", message);
    }

    @Override
    public void updateUserAfterVerification(User user) {
        user.setHasActivatedAccount(true);
        studentService.update(user);
    }
}
