package pl.dinosaurus.dinosauruski.slot;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dinosaurus.dinosauruski.model.Slot;

import java.util.List;

public interface SlotRepository extends JpaRepository<Slot, Long> {

    List<Slot> findAllByTeacherIdAndArchivedFalse(Long teacherId);


}
