package pl.dinosaurus.dinosauruski.registration;

import antlr.TokenStreamException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.dinosaurus.dinosauruski.model.User;
import pl.dinosaurus.dinosauruski.user.UserDto;
import pl.dinosaurus.dinosauruski.user.UserFactory;
import pl.dinosaurus.dinosauruski.user.UserService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private final UserService userService;
    private final UserFactory userFactory;


    @GetMapping("/register")
    public String registrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        model.addAttribute("types", types());
        return "register/form";
    }


    @PostMapping("/register")
    public String registerUserAccount(
            @ModelAttribute("user") @Valid UserDto userDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register/form";
        }

        if (!emailIsUnique(userDto)) {
            model.addAttribute("errorMessage", "użytkownik z takim adresem email już istnieje");
            return "login";
        }

        if (!passwordsAreTheSame(userDto)) {
            model.addAttribute("passwordError", "hasła są różne");
            return "register/form";
        }

        User user = userFactory.getUser(userDto);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setNickname(userDto.getNickname());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setType(userDto.getType());
        registrationService.saveUserBeforeEmailVerification(user);
        return "register/confirm";
    }

    private boolean emailIsUnique(UserDto userDto) {
        String email = userDto.getEmail();
        boolean emailAlreadyExist = userService.emailAlreadyExist(email);
        return !emailAlreadyExist;
    }

    private boolean passwordsAreTheSame(UserDto userDto) {
        String password = userDto.getPassword();
        String repeatedPassword = userDto.getRepeatedPassword();
        return password.equals(repeatedPassword);
    }

    @GetMapping("/register/confirm")
    public String confirmRegistration(@RequestParam("token") String token) {
        VerificationToken verificationToken;
        try {
            verificationToken = registrationService.getVerificationTokenOrThrow(token);
        } catch (TokenStreamException e) {
            return "register/fail";
        }

        User user = verificationToken.getUser();
        registrationService.updateUserAfterVerification(user);
        return "redirect:/login";
    }

    @ModelAttribute("types")
    public List<String> types() {
        return Arrays.asList("teacher", "student");
    }
}