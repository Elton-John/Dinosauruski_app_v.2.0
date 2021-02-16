package pl.dinosaurus.dinosauruski.teacherStudent;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dinosaurus.dinosauruski.model.Student;
import pl.dinosaurus.dinosauruski.registration.RegisterService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
class TeacherStudentServiceImplTest {

    @Mock
    RegisterService registerService;

    @Mock
    TeacherStudentRepository teacherStudentRepository;

    @InjectMocks
    TeacherStudentServiceImpl teacherStudentService;
    @Test
    void newlyCreatedStudentShouldBeCompleteBeforeRegistration(){
        //given
        Student student = new Student();
        student.setEmail("student@gmail.com");
        student.setFirstName("");
        student.setLastName("");

        //when
        teacherStudentService.createNewStudentByTeacher(student);
        //them
        verify(registerService).saveUserBeforeEmailVerification(student);
        assertNotNull(student.getPassword());
        assertThat(student.getFirstName(), not(blankOrNullString()));
//        assertNotNull(student.getFirstName());
//        assertNotNull(student.getLastName());
//        assertNotNull(student.getNickname());
        assertThat(student.getType(),is("student"));
    }
}