package pl.dinosaurus.dinosauruski.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "years")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class Year {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1900)
    @Max(3000)
    private Integer year;

    @NotNull
    @org.hibernate.annotations.Type(type = "true_false")
    private Boolean isGenerated;

    @NotNull
    @org.hibernate.annotations.Type(type = "true_false")
    private Boolean isArchived;

    @ToString.Exclude
    @ManyToOne
    private Teacher teacher;
}
