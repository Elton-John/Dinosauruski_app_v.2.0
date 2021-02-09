package pl.dinosaurus.dinosauruski.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "years")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class YearInCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(1900)
    @Max(3000)
    private Integer year;

    @NotNull
    @org.hibernate.annotations.Type(type = "true_false")
    private Boolean isArchived;

    @NotNull
    @ToString.Exclude
    @ManyToOne
    private Teacher teacher;

    @ToString.Exclude
    @OneToMany(mappedBy = "yearInCalendar")
    private Set<WeekInCalendar> weeks;

    @PrePersist
    public void prePersist() {
        isArchived = false;
    }

    @Override
    public String toString() {
        return "YearInCalendar{" +
                "id=" + id +
                ", year=" + year +
                ", isArchived=" + isArchived +
                ", teacher=" + teacher.getId() +
                ", weeks=" + weeks.size() +
                '}';
    }

    public List<LocalDate> generateMondayDatesOfYear() {
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
