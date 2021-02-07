package pl.dinosaurus.dinosauruski.registration;

import antlr.TokenStreamException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dinosaurus.dinosauruski.email.EmailService;
import pl.dinosaurus.dinosauruski.model.Teacher;
import pl.dinosaurus.dinosauruski.model.User;
import pl.dinosaurus.dinosauruski.user.UserService;
import pl.dinosaurus.dinosauruski.year.YearService;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final VerificationTokenRepository tokenRepository;
    private final EmailService emailService;
    private final YearService yearService;

    public void saveUserBeforeEmailVerification(User user) {
        user.setHasActivatedAccount(false);
        userService.saveNewUser(user);
        sendVerificationTokenByEmail(user);
    }

    private void sendVerificationTokenByEmail(User user) {
        VerificationToken token = createVerificationToken(user);
        String confirmationUrl = "/register/confirm?token=" + token.getToken();
        String message = "Cześć " + user.getNickname() + ", potwierdż swoją rejestrację klikając w poniższy link" + "\r\n" + "http://localhost:8080" + confirmationUrl;
        emailService.send(user.getEmail(), "Potwierdź rejestrację", message);
    }

    public VerificationToken createVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken userToken = new VerificationToken(token, user, 24);
        tokenRepository.save(userToken);
        return userToken;
    }

    public VerificationToken getVerificationTokenOrThrow(String VerificationToken) throws TokenStreamException {
        VerificationToken token = tokenRepository.findByToken(VerificationToken);
        if (token == null || token.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new TokenStreamException("token expired");
        }
        return token;
    }

    public void updateUserAfterVerification(User user) {
        user.setHasActivatedAccount(true);
        if (user.getType().equals("teacher")) {
            int year = Year.now().getValue();
            yearService.setYearInCalendarForTeacher(year, (Teacher) user);
        }
        userService.update(user);
    }
}
