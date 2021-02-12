package pl.dinosaurus.dinosauruski.teacherRole.teacher;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dinosaurus.dinosauruski.model.Slot;
import pl.dinosaurus.dinosauruski.slot.SlotService;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final SlotService slotService;


    @Override
    public List<Slot> findAllFreeSlotsByTeacherId(Long teacherId) {
        return slotService.findAllNotBookedSlotsByTeacherId(teacherId);
    }
}

