package pl.dinosaurus.dinosauruski.teacherRole.teacher;

import org.springframework.stereotype.Service;
import pl.dinosaurus.dinosauruski.model.Slot;
import pl.dinosaurus.dinosauruski.model.User;
import pl.dinosaurus.dinosauruski.user.UserService;

import java.util.List;
import java.util.Optional;

@Service
public interface TeacherService extends UserService {


    List<Slot> findAllFreeSlotsByTeacherId(Long teacherId);

    Optional<User> getNotConfirmedEmailForTeacher(String email);

    void delete(User user);
}
