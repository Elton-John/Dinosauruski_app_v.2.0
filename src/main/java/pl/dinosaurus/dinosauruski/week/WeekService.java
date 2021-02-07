package pl.dinosaurus.dinosauruski.week;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dinosaurus.dinosauruski.model.WeekInCalendar;
import pl.dinosaurus.dinosauruski.model.YearInCalendar;

import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class WeekService {

    private final WeekRepository weekRepository;

    public void setWeeksInCalendarForYear(YearInCalendar yearInCalendar) {
        Set<WeekInCalendar> weeks = createWeeksInCalendarForYear(yearInCalendar);
        weeks.forEach(weekRepository::save);
    }

    protected Set<WeekInCalendar> createWeeksInCalendarForYear(YearInCalendar yearInCalendar) {
        Set<WeekInCalendar> weeks = new HashSet<>();

        List<LocalDate> mondays = yearInCalendar.generateMondayDatesOfYear();
        mondays.forEach(date -> {
            WeekInCalendar week = new WeekInCalendar();
            week.setMondayDate(date);
            week.setNumberOfWeek(date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
            week.setIsArchived(false);
            week.setIsGenerated(false);
            week.setYearInCalendar(yearInCalendar);
            weeks.add(week);
        });
        return weeks;
    }
}
