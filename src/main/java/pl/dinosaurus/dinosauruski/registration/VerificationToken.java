package pl.dinosaurus.dinosauruski.registration;

import lombok.*;
import pl.dinosaurus.dinosauruski.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "verification_tokens")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime expiryDate;

    @ToString.Exclude
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "id")
    private User user;

    public VerificationToken(String token, User user, int hours) {
        this.token = token;
        this.user = user;
        expiryDate = calculateExpiryDate(hours);
    }

    private LocalDateTime calculateExpiryDate(int expiryTimeInHours) {
        LocalDateTime now = LocalDateTime.now();
        return now.plusHours(expiryTimeInHours);
    }
}