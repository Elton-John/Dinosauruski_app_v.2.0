package pl.dinosaurus.dinosauruski.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "slots")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private DayOfWeek dayOfWeek;

    @NotNull
    private LocalTime time;

    @NotNull
    private boolean booked;

    @NotNull
    private boolean archived;

    @ToString.Exclude
    @ManyToOne
    private Teacher teacher;

    @ToString.Exclude
    @Valid
    @ManyToOne()
    private Student regularStudent;
}
