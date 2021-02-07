package pl.dinosaurus.dinosauruski;

import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.dinosaurus.dinosauruski.model.CurrentUser;
import pl.dinosaurus.dinosauruski.model.Teacher;
import pl.dinosaurus.dinosauruski.model.YearInCalendar;
import pl.dinosaurus.dinosauruski.year.YearService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;

@Controller
@AllArgsConstructor
public class HomeController {

    private final SampleDataService sampleDataService;
    private final YearService yearService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login-error")
    public String login(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                errorMessage = ex.getMessage();
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    @Secured("ROLE_TEACHER")
    @GetMapping("/teacher/cockpit")
    public String cockpit(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        Teacher user = (Teacher) customUser.getUser();
        Set<YearInCalendar> years = yearService.getYearsByTeacherId(user.getId());
        model.addAttribute("years", years);
        return "teacher/cockpit";
    }

    @Secured("ROLE_STUDENT")
    @GetMapping("/student/profile")
    public String profile() {
        return "student/profile";
    }

    @GetMapping("/sampleData")
    public String loadData() {
        sampleDataService.addSampleTeacher();
        sampleDataService.addSampleStudent();
        return "index";
    }

}
