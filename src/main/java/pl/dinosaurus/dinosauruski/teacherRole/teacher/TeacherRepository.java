package pl.dinosaurus.dinosauruski.teacherRole.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dinosaurus.dinosauruski.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {


}
