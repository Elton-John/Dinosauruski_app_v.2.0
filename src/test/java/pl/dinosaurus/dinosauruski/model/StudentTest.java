package pl.dinosaurus.dinosauruski.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class StudentTest {

    private static Validator validator;
    private Student student;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeEach
    public void setValidTestObject() {
        student = new Student();
        student.setFirstName("Elton");
        student.setLastName("John");
        student.setNickname("star");
        student.setEmail("elton@gmail.com");
        student.setPassword("123");
        student.setPriceForOneLesson(new BigDecimal("100.00"));
        student.setOverpayment(new BigDecimal("000.00"));
        student.setHasActivatedAccount(true);
        student.setActive(true);

    }

    @Test
    void priceShouldBeGreaterThanZero() {
        //given  //valid object initialized in @BeforeEach

        //when
        student.setPriceForOneLesson(new BigDecimal("000.00"));
        Set<ConstraintViolation<Student>> violations = validator.validate(student);

        //then
        violations.forEach(action -> assertThat(action.getMessage(), is("must be greater than 0.0")));
    }

}