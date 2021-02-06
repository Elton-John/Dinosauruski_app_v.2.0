package pl.dinosaurus.dinosauruski.yearGenerator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dinosaurus.dinosauruski.model.Teacher;
import pl.dinosaurus.dinosauruski.model.User;
import pl.dinosaurus.dinosauruski.model.YearGenerator;

import java.util.Collections;
import java.util.HashSet;

@Transactional
@Service
@AllArgsConstructor
public class YearGeneratorService {


    private YearGeneratorRepository yearGeneratorRepository;

    public void setCurrentYearGenerationForNewTeacher(User user) {
        YearGenerator yearGenerator = new YearGenerator();
        yearGenerator.setYear(yearGenerator.getCurrentYear());
        yearGenerator.setIsGenerated(false);
        yearGenerator.setIsArchived(false);
        yearGenerator.setTeacher((Teacher) user);
        ((Teacher) user).setYears(new HashSet<>(Collections.singletonList(yearGenerator)));
        yearGeneratorRepository.save(yearGenerator);


    }
}
