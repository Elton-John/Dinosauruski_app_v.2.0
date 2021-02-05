package pl.dinosaurus.dinosauruski.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "individual_classes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class IndividualClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DecimalMin(value = "0.0", inclusive = false, message = "must be greater than 0.0")
    @Digits(integer = 3, fraction = 2)
    private BigDecimal priceForOneLesson;

    @DecimalMin(value = "0.0")
    @Digits(integer = 5, fraction = 2)
    private BigDecimal overpayment;

    @NotNull
    private Boolean active;

    @ManyToOne
    private Teacher teacher;

    @ManyToOne
    private Student student;
}
