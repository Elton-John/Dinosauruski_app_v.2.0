package pl.dinosaurus.dinosauruski.teacherStudent;

import pl.dinosaurus.dinosauruski.model.Student;

import java.util.List;

public interface TeacherStudentService {

    List<Student> findAllStudentsByTeacherId(Long id);

    Student createNewStudentByTeacher(Student student);
}
