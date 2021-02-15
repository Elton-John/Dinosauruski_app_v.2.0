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

public class Student extends User {

    @Id
    private Long id;

    @OneToMany(mappedBy = "student")
    private Set<IndividualClass> classes;

    @ManyToMany(mappedBy = "students")
    private Set<Teacher> teachers = new HashSet<>();

    @OneToMany(mappedBy = "regularStudent")
    private Set<Slot> slots = new HashSet<>();

    @OneToMany(mappedBy = "student")
    private Set<Payment> payments = new HashSet<>();

    @OneToMany(mappedBy = "notRegularStudent")
    private Set<Rebooking> rebookings = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

}
