package pl.dinosaurus.dinosauruski.password;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.dinosaurus.dinosauruski.model.User;
import pl.dinosaurus.dinosauruski.registration.VerificationTokenService;
import pl.dinosaurus.dinosauruski.user.UserService;

import javax.validation.Valid;

@Controller
public class PasswordResetController {

    private final VerificationTokenService tokenService;
    private final UserService userService;

    public PasswordResetController(VerificationTokenService tokenService,
                                   @Qualifier("userServiceImpl") UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @GetMapping("/password/reset/new")
    public String resetPasswordNewForm(@RequestParam("token") String token, Model model) {
        if (tokenService.tokenExist(token)) {
            model.addAttribute("token", token);
            model.addAttribute("passwords", new PasswordResetDto());
            return "password/new";
        }
        return "redirect:/";
    }

    @PostMapping("/password/reset/new")
    public String resetPasswordNew(@ModelAttribute("token") String token,
                                   @Valid @ModelAttribute("passwords") PasswordResetDto dto,
                                   BindingResult result,
                                   Model model) {
        if (result.hasErrors()) {
            model.addAttribute("token", token);
            return "password/new";
        }
        if (!dto.areTheSame()) {
            model.addAttribute("message", "Hasła są różne");
            model.addAttribute("token", token);
            return "password/new";
        }

        User user = tokenService.getUser(token);
        userService.resetPassword(user, dto.getPassword());
        return "redirect:/login";
    }
}
