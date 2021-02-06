package pl.dinosaurus.dinosauruski.model;

import lombok.*;
import pl.dinosaurus.dinosauruski.security.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String username;

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
    @Email
    @Column(nullable = false)
    @Size(max = 50)
    private String email;

    @NotBlank
    @Column(nullable = false)
    @Size(min = 3, max = 150)
    private String password;

    @NotNull
    private Boolean hasActivatedAccount;

    @NotBlank
    @Column(nullable = false)
    private String type;

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
