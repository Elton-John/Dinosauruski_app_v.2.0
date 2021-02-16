package pl.dinosaurus.dinosauruski.teacherStudent;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.dinosaurus.dinosauruski.individualClass.IndividualClassService;
import pl.dinosaurus.dinosauruski.model.CurrentUser;
import pl.dinosaurus.dinosauruski.model.Student;
import pl.dinosaurus.dinosauruski.model.User;
import pl.dinosaurus.dinosauruski.student.StudentService;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class TeacherStudentController {

    private final StudentService studentService;
    private final IndividualClassService individualClassService;
    private final TeacherStudentService teacherStudentService;

    @GetMapping("/teacher/students/find")
    public String findStudentForm() {
        return "teacher/students/find";
    }

    @PostMapping("/teacher/students/find")
    public String findStudent(@AuthenticationPrincipal CurrentUser currentUser,
                              @RequestParam("email") String email,
                              Model model) {
        boolean studentExists = studentService.userAlreadyExistsByEmail(email);
        if (studentExists) {
            Optional<User> optionalStudent = studentService.findByEmail(email);
            User student = optionalStudent.get();
            User teacher = currentUser.getUser();
            boolean hasClass = individualClassService.existClassByTeacherAndStudent(teacher.getId(), student.getId());
            model.addAttribute("student", student);
            model.addAttribute("hasClass", hasClass);
            return "teacher/students/one";
        }
        return "redirect:/teacher/students/new";
    }

    @GetMapping("/teacher/students/individual/add/{id}")
    public String addToClass() {
        return "teacher/students/individual/add";
    }


    @GetMapping("/teacher/students/new")
    public String newStudentForm(Model model) {
        StudentCreationByTeacherDto dto = new StudentCreationByTeacherDto();
        model.addAttribute("student", dto);
        return "teacher/students/new";
    }

    @PostMapping("/teacher/students/new")
    public String creatNewStudent(@ModelAttribute("student") StudentCreationByTeacherDto dto, Model model) {
        Student student = new Student();
        student.setEmail(dto.getEmail());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        Student savedStudent = teacherStudentService.createNewStudentByTeacher(student);
        model.addAttribute("student", savedStudent);
        model.addAttribute("hasClass", false);
        model.addAttribute("message", true);
        return "teacher/students/one";
    }

}
