package pl.dinosaurus.dinosauruski.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "lessons")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {"slot", "payment", "week", "rebooking",})
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private Boolean cancelledByTeacher;

    private Boolean cancelledByStudent;

    private Boolean cancelledLastMinute;

    private Boolean rebooked;

    @NotNull
    private Boolean archived;

    private Boolean paid;

    @NotNull
    private Boolean requiredPayment;

    @DecimalMin(value = "0.0")
    @Digits(integer = 5, fraction = 2)
    private BigDecimal addedPayment;

    @Valid
    @ManyToOne
    private Slot slot;

    @Valid
    @ManyToOne()
    private WeekInCalendar week;

    @OneToOne(mappedBy = "lesson")
    private Rebooking rebooking;

    @Valid
    @ManyToOne
    private Payment payment;

//    @PrePersist
//    public void prePersist() {
//       cancelledByTeacher = false;
//       cancelledByStudent = false;
//       cancelledLastMinute = false;
//       paid = false;
//       rebooked = false;
//    }
}
