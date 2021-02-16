package pl.dinosaurus.dinosauruski.teacherRole;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.dinosaurus.dinosauruski.model.CurrentUser;
import pl.dinosaurus.dinosauruski.model.Slot;
import pl.dinosaurus.dinosauruski.model.Student;
import pl.dinosaurus.dinosauruski.model.Teacher;
import pl.dinosaurus.dinosauruski.student.StudentService;
import pl.dinosaurus.dinosauruski.teacherRole.teacher.TeacherService;
import pl.dinosaurus.dinosauruski.teacherStudent.TeacherStudentService;

import java.util.List;

@Controller
@Secured("ROLE_TEACHER")
public class CockpitController {

    private final StudentService studentService;
    private final TeacherService teacherService;
    private final TeacherStudentService teacherStudentService;

    public CockpitController(StudentService studentService, TeacherService teacherService, TeacherStudentService teacherStudentService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.teacherStudentService = teacherStudentService;
    }

    @GetMapping("/teacher/cockpit")
    public String cockpit(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        Teacher teacher = (Teacher) currentUser.getUser();
        model.addAttribute("teacher", teacher);
        List<Slot> slots = teacherService.findAllFreeSlotsByTeacherId(teacher.getId());
        model.addAttribute("freeSlots", slots);
        return "teacher/cockpit/home";
    }

    @GetMapping("teacher/students")
    public String studentsList(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        Teacher teacher = (Teacher) currentUser.getUser();
        model.addAttribute("teacher", teacher);
        List<Student> students = teacherStudentService.findAllStudentsByTeacherId(teacher.getId());
        model.addAttribute("students", students);
        return "teacher/cockpit/students";
    }

}
