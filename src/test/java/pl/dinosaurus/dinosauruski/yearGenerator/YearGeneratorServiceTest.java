package pl.dinosaurus.dinosauruski.yearGenerator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pl.dinosaurus.dinosauruski.model.Teacher;
import pl.dinosaurus.dinosauruski.model.YearGenerator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.hamcrest.CoreMatchers.*;
@ExtendWith(MockitoExtension.class)
class YearGeneratorServiceTest {


    @Mock
    private YearGeneratorRepository yearRepository;

    @InjectMocks
    private YearGeneratorService yearGeneratorService;



    @Test
    void newlyCreatedTeacherAfterVerificationShouldHaveYearGeneratorByCurrentYearSet() {
        //given
        Teacher teacher = new Teacher();
        YearGenerator yearGenerator = new YearGenerator();
        yearGenerator.setYear(2021);
        yearGenerator.setTeacher(teacher);
        yearGenerator.setIsGenerated(false);
        yearGenerator.setIsArchived(false);

        //when
        yearGeneratorService.setCurrentYearGenerationForNewTeacher(teacher);

        //then
        assertThat(teacher.getYears(), contains(yearGenerator));
        assertThat(teacher.getYears().size(), is(1));
    }

    @Test
    void newlyCreatedTeacherAfterVerificationShouldHaveOnlyOneYearGeneratorByCurrentYearSet() {
        //given
        Teacher teacher = new Teacher();
        YearGenerator yearGenerator = new YearGenerator();
        yearGenerator.setYear(2021);
        yearGenerator.setTeacher(teacher);
        yearGenerator.setIsGenerated(false);
        yearGenerator.setIsArchived(false);

        //when
        yearGeneratorService.setCurrentYearGenerationForNewTeacher(teacher);

        //then
        assertThat(teacher.getYears(), contains(yearGenerator));
        assertThat(teacher.getYears().size(), is(1));
    }

    @Test
    void newlyCreatedTeacherShouldHaveYearGeneratorWithGeneratedFalseSet() {
        //given
        Teacher teacher = new Teacher();
        YearGenerator yearGenerator = new YearGenerator();
        yearGenerator.setYear(2021);
        yearGenerator.setTeacher(teacher);
        yearGenerator.setIsGenerated(false);
        yearGenerator.setIsArchived(false);

        //when
        yearGeneratorService.setCurrentYearGenerationForNewTeacher(teacher);

        //then
        teacher.getYears().forEach(yearGenerator1 -> assertFalse(yearGenerator1.getIsGenerated()));

    }
}
