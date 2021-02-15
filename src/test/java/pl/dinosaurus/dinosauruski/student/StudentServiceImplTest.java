package pl.dinosaurus.dinosauruski.student;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dinosaurus.dinosauruski.model.Student;
import pl.dinosaurus.dinosauruski.registration.RegistrationService;

import javax.imageio.spi.RegisterableService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    RegistrationService registrationService;

    @InjectMocks
    StudentServiceImpl studentService ;

//    @Test
//     void newlyCreatedStudentShouldBeCompleteBeforeRegistration(){
//        //given
//        Student student = new Student();
//        student.setEmail("student@gmail.com");
//
//        //when
//        studentService.createNewStudentByTeacher(student,1L);
//        //them
//        verify(registrationService).saveUserBeforeEmailVerification(student);
//        assertNotNull(student.getPassword());
//        assertNotNull(student.getFirstName());
//        assertNotNull(student.getLastName());
//        assertNotNull(student.getNickname());
//        assertThat(student.getType(),is("student"));
//    }

}