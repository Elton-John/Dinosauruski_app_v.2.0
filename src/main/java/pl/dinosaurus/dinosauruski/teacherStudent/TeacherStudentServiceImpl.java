package pl.dinosaurus.dinosauruski.teacherStudent;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.dinosaurus.dinosauruski.model.Student;
import pl.dinosaurus.dinosauruski.registration.RegisterService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class TeacherStudentServiceImpl implements TeacherStudentService {

    private final TeacherStudentRepository teacherStudentRepository;
    private final RegisterService registerService;

    public TeacherStudentServiceImpl(TeacherStudentRepository teacherStudentRepository,
                                     @Qualifier("studentRegisterServiceImpl") RegisterService registerService) {
        this.teacherStudentRepository = teacherStudentRepository;
        this.registerService = registerService;
    }

    @Override
    public List<Student> findAllStudentsByTeacherId(Long id) {
        return teacherStudentRepository.findAllByTeacherId(id);
    }

    @Override
    public Student createNewStudentByTeacher(Student student) {
        student.setPassword(generatePassword());
        student.setNickname("Noname");
        student.setType("student");
        registerService.saveUserBeforeEmailVerification(student);
        return student;
    }

    private String generatePassword() {
        String upperCaseLetters = RandomStringUtils.random(2, 65, 90, true, true);
        String lowerCaseLetters = RandomStringUtils.random(2, 97, 122, true, true);
        String numbers = RandomStringUtils.randomNumeric(2);
        String specialChar = RandomStringUtils.random(2, 33, 47, false, false);
        String totalChars = RandomStringUtils.randomAlphanumeric(2);
        String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
                .concat(numbers)
                .concat(specialChar)
                .concat(totalChars);
        List<Character> pwdChars = combinedChars.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        Collections.shuffle(pwdChars);
        return pwdChars.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
}
