package pl.dinosaurus.dinosauruski.teacherStudent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentCreationByTeacherDto {

    private String firstName;

    private String lastName;

    @NotBlank
    @Email
    @Size(max = 50)
    private String email;

}
