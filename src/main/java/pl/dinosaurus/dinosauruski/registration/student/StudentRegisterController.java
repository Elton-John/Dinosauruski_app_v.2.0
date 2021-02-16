package pl.dinosaurus.dinosauruski.registration.student;

import antlr.TokenStreamException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.dinosaurus.dinosauruski.model.User;
import pl.dinosaurus.dinosauruski.registration.RegisterService;
import pl.dinosaurus.dinosauruski.model.VerificationToken;
import pl.dinosaurus.dinosauruski.registration.VerificationTokenService;

@Controller
public class StudentRegisterController {

    private final RegisterService registerService;
    private final VerificationTokenService tokenService;

    public StudentRegisterController(@Qualifier("studentRegisterServiceImpl") RegisterService registerService, VerificationTokenService tokenService) {
        this.registerService = registerService;
        this.tokenService = tokenService;
    }

    @GetMapping("/register/student/confirm")
    public String confirmStudentRegistration(@RequestParam("token") String token, Model model) {
        VerificationToken verificationToken;
        try {
            verificationToken = tokenService.getVerificationTokenOrThrow(token);
        } catch (TokenStreamException e) {
            return "register/fail";
        }
        User user = verificationToken.getUser();
        registerService.updateUserAfterVerification(user);
       // model.addAttribute("token", token);
        return "redirect:/password/reset/new?token=" + token;
    }
}
