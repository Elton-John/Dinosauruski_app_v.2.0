package pl.dinosaurus.dinosauruski.week;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dinosaurus.dinosauruski.model.Teacher;
import pl.dinosaurus.dinosauruski.model.User;
import pl.dinosaurus.dinosauruski.model.WeekInCalendar;
import pl.dinosaurus.dinosauruski.model.YearInCalendar;

import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class WeekServiceTest {

    @Mock
    private WeekRepository weekRepository;
    @InjectMocks
    private WeekService weekService;

    @Test
    void shouldBeAbleGenerateWeeksInCalendarForYear() {
        //given
        User teacher = new Teacher();
        YearInCalendar yearInCalendar = new YearInCalendar();
        yearInCalendar.setTeacher((Teacher) teacher);
        yearInCalendar.setYear(2021);
        //when
        Set<WeekInCalendar> weeksInCalendarForYear = weekService.createWeeksInCalendarForYear(yearInCalendar);
        //then
        assertThat(weeksInCalendarForYear.size(), is(52));
    }

    @Test
    void newlyGeneratedWeeksShouldBeSaved() {
        //given
        User teacher = new Teacher();
        YearInCalendar yearInCalendar = new YearInCalendar();
        yearInCalendar.setTeacher((Teacher) teacher);
        yearInCalendar.setYear(2021);
        //when
        weekService.setWeeksInCalendarForYear(yearInCalendar);
        //then
        Set<WeekInCalendar> weeksInCalendarForYear = weekService.createWeeksInCalendarForYear(yearInCalendar);
        weeksInCalendarForYear.forEach(weekInCalendar -> {
            verify(weekRepository).save(weekInCalendar);
        });
    }

    @Test
    void newlyGeneratedWeeksShouldHaveProperFieldsSet() {
        //given
        User teacher = new Teacher();
        YearInCalendar yearInCalendar = new YearInCalendar();
        yearInCalendar.setTeacher((Teacher) teacher);
        yearInCalendar.setYear(2021);
        //when
        Set<WeekInCalendar> weeksInCalendarForYear = weekService.createWeeksInCalendarForYear(yearInCalendar);
        //then
        weeksInCalendarForYear.forEach(weekInCalendar -> {
            assertNotNull(weekInCalendar.getMondayDate());
            assertNotNull(weekInCalendar.getNumberOfWeek());
            assertNotNull(weekInCalendar.getIsArchived());
            assertNotNull(weekInCalendar.getIsGenerated());
            assertNotNull(weekInCalendar.getYearInCalendar());
        });
    }

    @Test
    void newlyGeneratedWeeksShouldHaveProperNumbersOfWeek() {
        //given
        User teacher = new Teacher();
        YearInCalendar yearInCalendar = new YearInCalendar();
        yearInCalendar.setTeacher((Teacher) teacher);
        yearInCalendar.setYear(2021);
        //when
        Set<WeekInCalendar> weeksInCalendarForYear = weekService.createWeeksInCalendarForYear(yearInCalendar);
        //then
        Set<Integer> numbersOfWeek = weeksInCalendarForYear.stream()
                .map(WeekInCalendar::getNumberOfWeek)
                .collect(Collectors.toSet());
        numbersOfWeek.forEach(number -> assertThat(number, allOf(greaterThan(0), lessThan(53))));
    }
}
