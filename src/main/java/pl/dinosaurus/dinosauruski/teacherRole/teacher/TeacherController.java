package pl.dinosaurus.dinosauruski.teacherRole.teacher;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.dinosaurus.dinosauruski.model.User;
import pl.dinosaurus.dinosauruski.user.UserBasicEditionDTO;
import pl.dinosaurus.dinosauruski.user.UserService;

import javax.validation.Valid;

@Controller
public class TeacherController {

    private final UserService userService;

    public TeacherController(@Qualifier("teacherServiceImpl") UserService userService) {
        this.userService = userService;
    }

    @GetMapping("teacher/edit/{id}")
    public String editForm(Model model, @PathVariable("id") Long teacherId) {
        UserBasicEditionDTO dto = userService.getUserBasicEditionDtoById(teacherId);
        model.addAttribute("teacher", dto);
        return "teacher/edit";
    }

    @PostMapping("teacher/edit/{id}")
    public String edit(@PathVariable("id") Long teacherId, @ModelAttribute ("teacher") @Valid UserBasicEditionDTO dto, BindingResult result) {
        if (result.hasErrors()) {
            return "teacher/edit";
        }
        User teacher = userService.findById(teacherId);
        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());
        teacher.setNickname(dto.getNickname());
        userService.update(teacher);
        return "redirect:/teacher/cockpit";
    }
}
