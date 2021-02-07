package pl.dinosaurus.dinosauruski.year;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dinosaurus.dinosauruski.model.YearInCalendar;

import java.util.Set;

public interface YearRepository extends JpaRepository<YearInCalendar, Long> {

    Set<YearInCalendar> findAllByTeacherId(Long teacherId);


}
