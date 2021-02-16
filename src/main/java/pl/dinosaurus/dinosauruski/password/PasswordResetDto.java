package pl.dinosaurus.dinosauruski.password;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PasswordResetDto {
    @NotBlank
    @Column(nullable = false)
    @Size(min = 3, max = 150)
    private String password;
    @NotBlank
    @Column(nullable = false)
    @Size(min = 3, max = 150)
    private String confirmPassword;

    public boolean areTheSame(){
        return password.equals(confirmPassword);
    }

}
