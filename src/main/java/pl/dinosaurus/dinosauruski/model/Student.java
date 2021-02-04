package pl.dinosaurus.dinosauruski.model;

import lombok.*;
import pl.dinosaurus.dinosauruski.security.Role;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    @Size(max = 25)
    private String firstName;

    @NotBlank
    @Column(nullable = false)
    @Size(max = 25)
    private String lastName;

    @NotBlank
    @Column(nullable = false)
    @Size(max = 25)
    private String nickname;

    @NotBlank
    @Column(nullable = false)
    @Size(max = 50)
    private String email;

    @NotBlank
    @Column(nullable = false)
    @Size(min = 3, max = 50)
    private String password;


    @DecimalMin(value = "0.0", inclusive = false, message = "must be greater than 0.0")
    @Digits(integer = 3, fraction = 2)
    private BigDecimal priceForOneLesson;

    @DecimalMin(value = "0.0")
    @Digits(integer = 5, fraction = 2)
    private BigDecimal overpayment;

    @NotNull
    private Boolean active;

    @NotNull
    private Boolean hasActivatedAccount;

    @ToString.Exclude
    @ManyToMany(mappedBy = "students")
    private Set<Teacher> teachers = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "regularStudent")
    private Set<Slot> slots = new TreeSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "student")
    private Set<Payment> payments = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "notRegularStudent")
    private Set<Rebooking> rebookings = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();


    public void addRole(Role role) {
        if (roles == null) {
            roles = new HashSet<>();
        }
        if (role != null) {
            roles.add(role);
        }
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
