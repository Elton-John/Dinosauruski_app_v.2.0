package pl.dinosaurus.dinosauruski.year;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dinosaurus.dinosauruski.model.Teacher;
import pl.dinosaurus.dinosauruski.week.WeekService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
class YearServiceTest {

    @Mock
    private YearRepository yearRepository;

    @Spy
    private WeekService weekService;

    @InjectMocks
    private YearService yearService;

    @Test
    void shouldBeAbleSetYearInCalendarForTeacher() {
        //given
        Teacher teacher = new Teacher();
        int year = 2021;
        //when
        yearService.setYearInCalendarForTeacher(year, teacher);
        //then
        assertThat(teacher.getYears().size(), is(1));
    }

    @Test
    void newlyCreatedYearInCalendarShouldHaveAllFieldsSet() {
        //given
        Teacher teacher = new Teacher();
        int year = 2021;
        //when
        yearService.setYearInCalendarForTeacher(year, teacher);
        //then
        teacher.getYears().forEach(yearInCalendar -> {
            assertNotNull(yearInCalendar.getIsGenerated());
            assertNotNull(yearInCalendar.getIsArchived());
            assertNotNull(yearInCalendar.getYear());
            assertNotNull(yearInCalendar.getWeeks());
            assertNotNull(yearInCalendar.getTeacher());
        });
    }

    @Test
    void newlyCreatedYearInCalendarShouldHaveGeneratedWeeks() {
        //given
        Teacher teacher = new Teacher();
        int year = 2021;
        //when
        yearService.setYearInCalendarForTeacher(year, teacher);
        //then
        teacher.getYears().forEach(yearInCalendar -> assertThat(yearInCalendar.getWeeks().size(), is(52)));
    }

}
