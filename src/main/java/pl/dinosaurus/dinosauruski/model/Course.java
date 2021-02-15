package pl.dinosaurus.dinosauruski.model;

import lombok.*;

import javax.persistence.*;

//@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
//@Table(name = "courses")
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@EqualsAndHashCode(of = {"id"})
//@ToString
public abstract class Course  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Teacher teacher;
}
