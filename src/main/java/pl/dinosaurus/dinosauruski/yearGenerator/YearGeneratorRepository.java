package pl.dinosaurus.dinosauruski.yearGenerator;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dinosaurus.dinosauruski.model.User;
import pl.dinosaurus.dinosauruski.model.YearGenerator;

public interface YearGeneratorRepository extends JpaRepository<YearGenerator, Long> {
}
