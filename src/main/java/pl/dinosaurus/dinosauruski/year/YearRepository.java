package pl.dinosaurus.dinosauruski.year;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dinosaurus.dinosauruski.model.YearInCalendar;

public interface YearRepository extends JpaRepository<YearInCalendar, Long> {
}
