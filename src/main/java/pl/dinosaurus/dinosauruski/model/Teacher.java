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
public class Teacher extends User {

    @Id
    private Long id;

    @ManyToMany
    @JoinTable(name = "teachers_students", joinColumns = @JoinColumn(name = "teacher_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students = new HashSet<>();

    @OneToMany(mappedBy = "teacher")
    private Set<Slot> slots = new HashSet<>();

    @OneToMany(mappedBy = "teacher")
    private Set<Payment> payments = new HashSet<>();

//    @OneToMany(mappedBy = "teacher")
//    private Set<WeekInCalendar> weeks = new HashSet<>();

    @OneToMany(mappedBy = "teacher")
    private Set<IndividualClass> individualClasses;

    @OneToMany(mappedBy = "teacher")
    private Set<YearInCalendar> years;

}
