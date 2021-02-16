package pl.dinosaurus.dinosauruski.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.dinosaurus.dinosauruski.model.Student;
import pl.dinosaurus.dinosauruski.model.User;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

   Optional<User> findByEmailAndType(String email, String type);

}
