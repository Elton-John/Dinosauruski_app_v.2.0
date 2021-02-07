package pl.dinosaurus.dinosauruski.year;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dinosaurus.dinosauruski.model.Teacher;
import pl.dinosaurus.dinosauruski.week.WeekService;

import java.time.Year;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
class YearServiceTest {

    @Mock
    private YearRepository yearRepository;

    @Spy
    private WeekService weekService;

    @InjectMocks
    private YearService yearService;

    @Test
    void newlyCreatedTeacherAfterVerificationShouldHaveYearYearInCalendarWithCurrentYearSet() {
        //given
        Teacher teacher = new Teacher();
        //when
        yearService.setCurrentYearGenerationForNewTeacher(teacher);
        //then
        teacher.getYears().forEach(yearInCalendar -> assertThat(yearInCalendar.getYear(), is(Year.now().getValue())));
    }

    @Test
    void newlyCreatedTeacherAfterVerificationShouldHaveOnlyOneYearInCalendarSet() {
        //given
        Teacher teacher = new Teacher();
        //when
        yearService.setCurrentYearGenerationForNewTeacher(teacher);
        //then
        assertThat(teacher.getYears().size(), is(1));
    }

    @Test
    void newlyCreatedTeacherShouldHaveYearGeneratorWithGeneratedTrueSet() {
        //given
        Teacher teacher = new Teacher();
        //when
        yearService.setCurrentYearGenerationForNewTeacher(teacher);
        //then
        teacher.getYears().forEach(yearInCalendar -> assertTrue(yearInCalendar.getIsGenerated()));
    }

    @Test
    void newlyCreatedYearInWeekShouldHaveWeeksSet() {
        //given
        Teacher teacher = new Teacher();
        //when
        yearService.setCurrentYearGenerationForNewTeacher(teacher);
        //then
        teacher.getYears().forEach(yearInCalendar -> assertNotNull(yearInCalendar.getWeeks()));

    }
}
