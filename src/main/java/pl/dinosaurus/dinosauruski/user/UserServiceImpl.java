package pl.dinosaurus.dinosauruski.user;

import lombok.AllArgsConstructor;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.dinosaurus.dinosauruski.model.User;
import pl.dinosaurus.dinosauruski.security.Role;
import pl.dinosaurus.dinosauruski.security.RoleRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean emailAlreadyExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveNewUser(User user) {
        setProperFields(user);
        userRepository.save(user);
    }

    private void setProperFields(User user) {
        setEncodedPassword(user);
        setRoleForNewUser(user);
    }

    private void setEncodedPassword(User user) {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    private void setRoleForNewUser(User user) {
        Role role;
        String userType = user.getType();
        switch (userType) {
            case "teacher":
                role = roleRepository.findFirstByNameIgnoringCase("ROLE_TEACHER");
                break;
            case "student":
                role = roleRepository.findFirstByNameIgnoringCase("ROLE_STUDENT");
                break;
            default:
                throw new InvalidPropertyException(User.class, userType, "invalid user type");
        }
        user.setRoles(new HashSet<>(Collections.singletonList(role)));
    }

    @Override
    public UserBasicEditionDTO getUserBasicEditionDtoById(Long teacherId) {
        Optional<User> optional = userRepository.findById(teacherId);
        User user = optional.orElseThrow(EntityNotFoundException::new);
        UserBasicEditionDTO dto = new UserBasicEditionDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setNickname(user.getNickname());
        return dto;
    }

    @Override
    public User findById(Long teacherId) {
        return userRepository.findById(teacherId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }
}
