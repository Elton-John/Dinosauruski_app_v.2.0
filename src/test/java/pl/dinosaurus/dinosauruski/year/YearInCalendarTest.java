package pl.dinosaurus.dinosauruski.year;

import org.junit.jupiter.api.Test;
import pl.dinosaurus.dinosauruski.model.YearInCalendar;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class YearInCalendarTest {


    @Test
    void shouldBeAbleGenerateListOfMondayDatesOfYear() {
        //given
        YearInCalendar yearInCalendar = new YearInCalendar();
        yearInCalendar.setYear(2021);
        int numberOfMondaysIn2021 = 52;
        //when
        List<LocalDate> mondays = yearInCalendar.generateMondayDatesOfYear();
        //then
        assertThat(mondays.size(), is(numberOfMondaysIn2021));
    }
}
