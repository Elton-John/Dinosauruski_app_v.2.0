package pl.dinosaurus.dinosauruski.registration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dinosaurus.dinosauruski.email.EmailService;
import pl.dinosaurus.dinosauruski.model.Teacher;
import pl.dinosaurus.dinosauruski.model.User;
import pl.dinosaurus.dinosauruski.user.UserService;
import pl.dinosaurus.dinosauruski.yearGenerator.YearGeneratorService;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {
    @Mock
    private UserService userService;
    @Mock
    private VerificationTokenRepository tokenRepository;
    @Mock
    private EmailService emailService;
    @Mock
    private YearGeneratorService yearGeneratorService;

    @InjectMocks
    RegistrationService registrationService;

    @DisplayName("if new user has teacher type then yearGeneration has set")
    @Test
    void newlyCreatedUserAfterVerificationShouldHaveYearGeneratorSetIfTypeIsTeacher() {
        //given
        User teacher = new Teacher();
        teacher.setId(1L);
        teacher.setType("teacher");

        User student = new Teacher();
        student.setId(2L);
        student.setType("student");

        //when  //then
        registrationService.saveUserAfterVerification(teacher);
        registrationService.saveUserAfterVerification(student);
        verify(yearGeneratorService).setCurrentYearGenerationForNewTeacher(teacher);
        verify(yearGeneratorService, never()).setCurrentYearGenerationForNewTeacher(student);

    }

}