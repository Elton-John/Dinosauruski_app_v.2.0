package pl.dinosaurus.dinosauruski;

import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class HomePageController {

    private final SampleDataService sampleDataService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/sampleData")
    public String loadData() {
        sampleDataService.addSampleTeacher();
        sampleDataService.addSampleStudent();
        return "index";
    }

    @Secured("ROLE_TEACHER")
    @GetMapping("/teacher/cockpit")
    public String cockpit() {
        return "teacher/cockpit";
    }

    @Secured("ROLE_STUDENT")
    @GetMapping("/student/profile")
    public String profile() {
        return "student/profile";
    }

}
