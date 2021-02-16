package pl.dinosaurus.dinosauruski.student;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dinosaurus.dinosauruski.model.Student;
import pl.dinosaurus.dinosauruski.registration.RegisterService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    RegisterService registerService;

    @InjectMocks
    StudentServiceImpl studentService ;



}