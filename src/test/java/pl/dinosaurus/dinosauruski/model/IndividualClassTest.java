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

class IndividualClassTest {

    private static Validator validator;
    private IndividualClass individualClass;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeEach
    public void setValidTestObject() {
        individualClass = new IndividualClass();
        individualClass.setPriceForOneLesson(new BigDecimal("100.00"));
        individualClass.setOverpayment(new BigDecimal("000.00"));
             individualClass.setActive(true);

    }

    @Test
    void priceShouldBeGreaterThanZero() {
        //given  //valid object initialized in @BeforeEach

        //when
        individualClass.setPriceForOneLesson(new BigDecimal("000.00"));
        Set<ConstraintViolation<IndividualClass>> violations = validator.validate(individualClass);

        //then
        violations.forEach(action -> assertThat(action.getMessage(), is("must be greater than 0.0")));
    }

}