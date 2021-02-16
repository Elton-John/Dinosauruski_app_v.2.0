package pl.dinosaurus.dinosauruski.teacherStudent;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.dinosaurus.dinosauruski.model.Student;
import pl.dinosaurus.dinosauruski.student.StudentRepository;

import java.util.List;

public interface TeacherStudentRepository extends StudentRepository {

    @Query("SELECT s FROM Student s  JOIN FETCH s.teachers t WHERE t.id = :id")
    List<Student> findAllByTeacherId(@Param("id") Long id);
}
