package pl.dinosaurus.dinosauruski.year;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dinosaurus.dinosauruski.model.Teacher;
import pl.dinosaurus.dinosauruski.model.YearInCalendar;
import pl.dinosaurus.dinosauruski.week.WeekService;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
@AllArgsConstructor
public class YearService {

    private final YearRepository yearRepository;
    private final WeekService weekService;

    public void setYearInCalendarForTeacher(int year, Teacher teacher) {
        YearInCalendar yearInCalendar = new YearInCalendar();
        yearInCalendar.setYear(year);
        yearInCalendar.setIsArchived(false);
        yearInCalendar.setTeacher(teacher);
        teacher.setYears(new HashSet<>(Collections.singletonList(yearInCalendar)));
        YearInCalendar savedYearInCalendar = yearRepository.save(yearInCalendar);
        weekService.setWeeksInCalendarForYear(savedYearInCalendar);
    }

    public Set<YearInCalendar> getYearsByTeacherId(Long teacherId) {
        return yearRepository.findAllByTeacherId(teacherId);
    }
}
