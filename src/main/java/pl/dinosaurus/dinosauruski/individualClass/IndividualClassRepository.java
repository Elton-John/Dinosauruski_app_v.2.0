package pl.dinosaurus.dinosauruski.individualClass;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dinosaurus.dinosauruski.model.IndividualClass;

import java.util.Optional;

public interface IndividualClassRepository extends JpaRepository<IndividualClass, Long> {

    Optional<IndividualClass> findFirstByTeacherIdAndStudentId(Long teacherId, Long studentId);
}
