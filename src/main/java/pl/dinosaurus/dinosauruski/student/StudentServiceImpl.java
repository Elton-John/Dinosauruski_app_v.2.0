package pl.dinosaurus.dinosauruski.student;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dinosaurus.dinosauruski.model.User;
import pl.dinosaurus.dinosauruski.security.RoleRepository;
import pl.dinosaurus.dinosauruski.user.UserRepository;
import pl.dinosaurus.dinosauruski.user.UserServiceImpl;

import java.util.Optional;

@Service
@Transactional
public class StudentServiceImpl extends UserServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(UserRepository userRepository,
                              RoleRepository roleRepository,
                              BCryptPasswordEncoder bCryptPasswordEncoder,
                              StudentRepository studentRepository) {
        super(userRepository, roleRepository, bCryptPasswordEncoder);
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return studentRepository.findByEmailAndType(email,"student");
    }

    @Override
    public boolean userAlreadyExistsByEmail(String email) {
        if (super.userAlreadyExistsByEmail(email)) {
            Optional<User> optionalUser = findByEmail(email);
            User user = optionalUser.get();
            return (user.getType().equals("student"));
        }
        return false;
    }

}
