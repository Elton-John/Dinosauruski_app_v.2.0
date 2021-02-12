package pl.dinosaurus.dinosauruski.slot;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Getter
@Setter
@ToString
public class NewSlotDto {

    @NotNull
    private DAY_OF_WEEK dayOfWeek;

    @NotNull
    private LocalTime time;

}
