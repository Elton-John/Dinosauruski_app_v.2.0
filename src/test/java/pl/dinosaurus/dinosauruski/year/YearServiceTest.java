package pl.dinosaurus.dinosauruski.year;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dinosaurus.dinosauruski.model.Teacher;
import pl.dinosaurus.dinosauruski.model.YearInCalendar;
import pl.dinosaurus.dinosauruski.week.WeekService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class YearServiceTest {

    @Mock
    private YearRepository yearRepository;
    @Mock
    private WeekService weekService;
    @InjectMocks
    private YearService yearService;


    @Test
    void shouldBeAbleSetYearInCalendarForTeacher() {
        //given
        Teacher teacher = new Teacher();
        int year = 2021;
        given(yearRepository.save(isA(YearInCalendar.class))).willReturn(new YearInCalendar());
        //when
        yearService.setYearInCalendarForTeacher(year, teacher);
        //then
        assertThat(teacher.getYears().size(), is(1));
    }

    @Test
    void newlyCreatedYearInCalendarShouldHaveProperFieldsSet() {
        //given
        Teacher teacher = new Teacher();
        int year = 2021;
        given(yearRepository.save(isA(YearInCalendar.class))).willReturn(new YearInCalendar());
        //when
        yearService.setYearInCalendarForTeacher(year, teacher);
        //then
        teacher.getYears().forEach(yearInCalendar -> {
            assertThat(yearInCalendar.getTeacher(), is(teacher));
            assertThat(yearInCalendar.getYear(), is(year));
        });
    }

    @Test
    void newlyCreatedYearShouldHaveWeeksSet() {
        //given
        Teacher teacher = new Teacher();
        int year = 2021;
        given(yearRepository.save(isA(YearInCalendar.class))).willReturn(new YearInCalendar());
        //when
        yearService.setYearInCalendarForTeacher(year, teacher);
        //then
        verify(weekService).setWeeksInCalendarForYear(isA(YearInCalendar.class));
    }

}
