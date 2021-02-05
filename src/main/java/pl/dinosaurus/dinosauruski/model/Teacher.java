package pl.dinosaurus.dinosauruski.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@PrimaryKeyJoinColumn(name = "teacher_id")
@Table(name = "teachers")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@ToString
public class Teacher extends User {

    @Id
    private Long id;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "teachers_students", joinColumns = @JoinColumn(name = "teacher_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "teacher")
    private Set<Slot> slots = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "teacher")
    private Set<Payment> payments = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "teacher")
    private Set<Week> weeks = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "teacher")
    private Set<IndividualClass> individualClasses;

    @OneToMany(mappedBy = "teacher")
    private Set<MyYear> myYears;

}
