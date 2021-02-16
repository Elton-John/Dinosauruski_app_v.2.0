package pl.dinosaurus.dinosauruski.registration.teacher;

import antlr.TokenStreamException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.dinosaurus.dinosauruski.model.User;
import pl.dinosaurus.dinosauruski.registration.RegisterService;
import pl.dinosaurus.dinosauruski.model.VerificationToken;
import pl.dinosaurus.dinosauruski.registration.VerificationTokenService;
import pl.dinosaurus.dinosauruski.teacherRole.teacher.TeacherService;
import pl.dinosaurus.dinosauruski.user.UserCreationDto;
import pl.dinosaurus.dinosauruski.user.UserFactory;
import pl.dinosaurus.dinosauruski.user.UserService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
public class TeacherRegisterController {

    private final RegisterService registerService;

    private final UserService userService;
    private final UserFactory userFactory;
    private final TeacherService teacherService;

    private final VerificationTokenService tokenService;

    public TeacherRegisterController(@Qualifier("teacherRegisterServiceImpl") RegisterService registerService,
                                     @Qualifier("userServiceImpl") UserService userService,
                                     UserFactory userFactory,
                                     TeacherService teacherService,
                                     VerificationTokenService tokenService) {
        this.registerService = registerService;
        this.userService = userService;
        this.userFactory = userFactory;
        this.teacherService = teacherService;
        this.tokenService = tokenService;
    }

    @GetMapping("/register")
    public String registrationForm(Model model) {
        model.addAttribute("user", new UserCreationDto());
        model.addAttribute("types", types());
        return "register/form";
    }

    @PostMapping("/register")
    public String registerUserAccount(
            @ModelAttribute("user") @Valid UserCreationDto userCreationDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register/form";
        }

        if (userCreationDto.getType().equals("teacher")) {
            if (teacherService.userAlreadyExistsByEmail(userCreationDto.getEmail())) {
                model.addAttribute("errorMessage", "teacher z takim adresem email już istnieje");
                return "user/login";
            }
        } else {
            if (userService.userAlreadyExistsByEmail(userCreationDto.getEmail())) {
                model.addAttribute("errorMessage", "student z takim adresem email już istnieje");
                return "user/login";
            }
        }

        if (!passwordsAreTheSame(userCreationDto)) {
            model.addAttribute("passwordError", "hasła są różne");
            return "register/form";
        }

        User user = userFactory.getUser(userCreationDto);
        user.setFirstName(userCreationDto.getFirstName());
        user.setLastName(userCreationDto.getLastName());
        user.setNickname(userCreationDto.getNickname());
        user.setEmail(userCreationDto.getEmail());
        user.setPassword(userCreationDto.getPassword());
        user.setType(userCreationDto.getType());

        registerService.saveUserBeforeEmailVerification(user);
        return "register/confirm";
    }

    private boolean passwordsAreTheSame(UserCreationDto userCreationDto) {
        String password = userCreationDto.getPassword();
        String repeatedPassword = userCreationDto.getRepeatedPassword();
        return password.equals(repeatedPassword);
    }

    @GetMapping("/register/confirm")
    public String confirmRegistration(@RequestParam("token") String token) {
        VerificationToken verificationToken;
        try {
            verificationToken = tokenService.getVerificationTokenOrThrow(token);
        } catch (TokenStreamException e) {
            return "register/fail";
        }
        User user = verificationToken.getUser();
        registerService.updateUserAfterVerification(user);
        return "redirect:/login";
    }

    @ModelAttribute("types")
    public List<String> types() {
        return Arrays.asList("teacher");
    }
}