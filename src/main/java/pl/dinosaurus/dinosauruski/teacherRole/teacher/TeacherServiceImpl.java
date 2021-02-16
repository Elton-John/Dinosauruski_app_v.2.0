package pl.dinosaurus.dinosauruski.teacherRole.teacher;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dinosaurus.dinosauruski.model.Slot;
import pl.dinosaurus.dinosauruski.model.Teacher;
import pl.dinosaurus.dinosauruski.model.User;
import pl.dinosaurus.dinosauruski.security.RoleRepository;
import pl.dinosaurus.dinosauruski.slot.SlotService;
import pl.dinosaurus.dinosauruski.user.UserRepository;
import pl.dinosaurus.dinosauruski.user.UserServiceImpl;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class TeacherServiceImpl extends UserServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final SlotService slotService;
    private final UserRepository userRepository;

    public TeacherServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder, TeacherRepository teacherRepository, SlotService slotService, UserRepository userRepository1) {
        super(userRepository, roleRepository, bCryptPasswordEncoder);
        this.teacherRepository = teacherRepository;
        this.slotService = slotService;
        this.userRepository = userRepository1;
    }

    @Override
    public boolean userAlreadyExistsByEmail(String email) {
        if (super.userAlreadyExistsByEmail(email)) {
            Optional<User> optionalUser = findByEmail(email);
            User user = optionalUser.get();
            return (user.getType().equals("teacher") && user.getHasActivatedAccount());
        }
        return false;
    }

    @Override
    public List<Slot> findAllFreeSlotsByTeacherId(Long teacherId) {
        return slotService.findAllNotBookedSlotsByTeacherId(teacherId);
    }

    @Override
    public Optional<User> getNotConfirmedEmailForTeacher(String email) {
        return teacherRepository.findByEmailAndHasActivatedAccountFalse(email);

    }

    @Override
    public void delete(User user) {
        teacherRepository.delete((Teacher) user);
        userRepository.delete(user);
    }
}

