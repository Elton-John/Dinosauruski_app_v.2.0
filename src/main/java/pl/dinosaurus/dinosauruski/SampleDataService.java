package pl.dinosaurus.dinosauruski;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dinosaurus.dinosauruski.model.Student;
import pl.dinosaurus.dinosauruski.model.Teacher;
import pl.dinosaurus.dinosauruski.model.User;
import pl.dinosaurus.dinosauruski.user.UserService;

@Service
@AllArgsConstructor
public class SampleDataService {

    private final UserService userService;

    public void addSampleTeacher() {
        User user = new Teacher();
        user.setUsername("elti");
        user.setPassword("0000");
        user.setFirstName("elton");
        user.setLastName("john");
        user.setEmail("elton@mail.ru");
        user.setNickname("superstar");
        user.setType("teacher");
        userService.saveNewUser(user);
    }


    public void addSampleStudent() {
        User user = new Student();
        user.setUsername("eli");
        user.setPassword("1111");
        user.setFirstName("elon");
        user.setLastName("musk");
        user.setEmail("elon@cosmos.ru");
        user.setNickname("mars");
        user.setType("student");
        userService.saveNewUser(user);
    }
}
