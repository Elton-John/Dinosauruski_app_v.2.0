package pl.dinosaurus.dinosauruski.user;

import org.springframework.beans.InvalidPropertyException;
import org.springframework.stereotype.Service;
import pl.dinosaurus.dinosauruski.model.Student;
import pl.dinosaurus.dinosauruski.model.Teacher;
import pl.dinosaurus.dinosauruski.model.User;

@Service
public class UserFactory {

    public User getUser(UserDto userDto) {
        String type = userDto.getType();
        switch (type) {
            case "teacher":
                return new Teacher();
            case "student":
                return new Student();
            default:
                throw new InvalidPropertyException(UserDto.class, type, "invalid user type");
        }
    }
}
