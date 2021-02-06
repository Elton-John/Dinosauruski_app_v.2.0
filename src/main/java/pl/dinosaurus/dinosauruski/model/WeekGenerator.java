package pl.dinosaurus.dinosauruski.model;

import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "weeks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class WeekGenerator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Range(min = 0, max = 60)
    private Integer numberOfWeek;

    @NotNull
    @Range(min = 1900, max = 3000)
    private Integer year;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate mondayDate;

    @NotNull
    @org.hibernate.annotations.Type(type = "true_false")
    private Boolean isGenerated;

    @NotNull
    @org.hibernate.annotations.Type(type = "true_false")
    private Boolean isArchived;

    @ToString.Exclude
    @ManyToOne
    private Teacher teacher;

    @ToString.Exclude
    @OneToMany(mappedBy = "week")
    private Set<Lesson> lessons = new HashSet<>();


    public List<LocalDate> generateMondayDatesOfYear(int year) {
        List<LocalDate> mondays = new ArrayList<>();

        int daysInYear = Year.of(year).length();
        for (int i = 1; i < daysInYear; i++) {
            LocalDate date = LocalDate.ofYearDay(year, i);
            if (date.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
                mondays.add(date);
            }
        }
        return mondays;
    }


}
