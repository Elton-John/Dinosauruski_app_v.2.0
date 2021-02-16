package pl.dinosaurus.dinosauruski.registration.teacher;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dinosaurus.dinosauruski.email.EmailService;
import pl.dinosaurus.dinosauruski.model.Teacher;
import pl.dinosaurus.dinosauruski.model.User;
import pl.dinosaurus.dinosauruski.registration.RegisterService;
import pl.dinosaurus.dinosauruski.model.VerificationToken;
import pl.dinosaurus.dinosauruski.registration.VerificationTokenService;
import pl.dinosaurus.dinosauruski.teacherRole.teacher.TeacherService;
import pl.dinosaurus.dinosauruski.year.YearService;

import java.time.Year;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class TeacherRegisterServiceImpl implements RegisterService {

    private final TeacherService teacherService;
    private final VerificationTokenService tokenService;
    private final EmailService emailService;
    private final YearService yearService;

    public User saveUserBeforeEmailVerification(User user) {
        cleanNotUniqueEmails(user.getEmail());
        User saved = teacherService.saveNewUser(user);
        sendVerificationTokenByEmail(saved);
        return saved;
    }

    private void cleanNotUniqueEmails(String email) {
        Optional<User> optionalUser = teacherService.getNotConfirmedEmailForTeacher(email);
        optionalUser.ifPresent(teacherService::delete);
    }

    public void sendVerificationTokenByEmail(User user) {
        VerificationToken token = tokenService.createVerificationToken(user);
        String confirmationUrl = "/register/confirm?token=" + token.getToken();
        String message = "Cześć " + user.getNickname() + ", potwierdż swoją rejestrację klikając w poniższy link" + "\r\n" + "http://localhost:8080" + confirmationUrl;
        emailService.send(user.getEmail(), "Potwierdź rejestrację", message);
    }

    public void updateUserAfterVerification(User user) {
        user.setHasActivatedAccount(true);
        int year = Year.now().getValue();
        yearService.setYearInCalendarForTeacher(year, (Teacher) user);
        teacherService.update(user);
    }
}
