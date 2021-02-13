package pl.dinosaurus.dinosauruski.slot;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FreeSlotDto {

    private Long id;

    @NotNull
    private DAY_OF_WEEK dayOfWeek;

    @NotNull
    private LocalTime time;

    public String getDayAndTimeInPolish() {
        return dayOfWeek.getInPolish() + " " + time;
    }

}
