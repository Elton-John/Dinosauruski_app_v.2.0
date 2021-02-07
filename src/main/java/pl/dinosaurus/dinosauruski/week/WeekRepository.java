package pl.dinosaurus.dinosauruski.week;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dinosaurus.dinosauruski.model.WeekInCalendar;

public interface WeekRepository extends JpaRepository<WeekInCalendar,Long> {
}
