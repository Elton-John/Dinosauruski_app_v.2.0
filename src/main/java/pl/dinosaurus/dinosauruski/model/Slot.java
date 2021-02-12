package pl.dinosaurus.dinosauruski.model;

import lombok.*;
import pl.dinosaurus.dinosauruski.slot.DAY_OF_WEEK;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity
@Table(name = "slots")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {"teacher", "regularStudent"})
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private DAY_OF_WEEK dayOfWeek;

    @NotNull
    private LocalTime time;

    @NotNull
    private boolean booked;

    @NotNull
    private boolean archived;

    @NotNull
    @ManyToOne
    private Teacher teacher;

    @Valid
    @ManyToOne()
    private Student regularStudent;
}
