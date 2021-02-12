package pl.dinosaurus.dinosauruski.slot;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dinosaurus.dinosauruski.model.Slot;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class SlotService {

    private final SlotRepository slotRepository;

    protected void saveNewSlot(Slot slot) {
        slotRepository.save(slot);
    }

    protected List<Slot> findAllSlotsByTeacherId(Long teacherId) {
        return slotRepository.findAllByTeacherIdAndArchivedFalse(teacherId);
    }

    public List<Slot> findAllNotBookedSlotsByTeacherId(Long teacherId) {
        return slotRepository.findAllByTeacherIdAndArchivedFalse(teacherId).stream()
                .filter(slot -> !slot.isBooked()).collect(Collectors.toList());
    }
}
