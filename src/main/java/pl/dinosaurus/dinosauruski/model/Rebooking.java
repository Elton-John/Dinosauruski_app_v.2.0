package pl.dinosaurus.dinosauruski.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
@Table(name = "rebookings")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class Rebooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ManyToOne
    private Student notRegularStudent;

    @Valid
    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private Lesson lesson;

    @Override
    public String toString() {
        return "Rebooking{" +
                "id=" + id +
                ", notRegularStudent=" + notRegularStudent.getId() +
                ", lesson=" + lesson.getId() +
                '}';
    }
}
