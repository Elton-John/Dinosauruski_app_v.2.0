package pl.dinosaurus.dinosauruski.model;

import lombok.*;
import pl.dinosaurus.dinosauruski.security.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "student_id")
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@ToString
public class Student extends User {

    @Id
    private Long id;

    @ToString.Exclude
    @OneToMany(mappedBy = "student")
    private Set<IndividualClass> individualClasses;

    @ToString.Exclude
    @ManyToMany(mappedBy = "students")
    private Set<Teacher> teachers = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "regularStudent")
    private Set<Slot> slots = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "student")
    private Set<Payment> payments = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "notRegularStudent")
    private Set<Rebooking> rebookings = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

}
