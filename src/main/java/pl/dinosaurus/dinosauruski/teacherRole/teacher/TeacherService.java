package pl.dinosaurus.dinosauruski.teacherRole.teacher;

import org.springframework.stereotype.Service;
import pl.dinosaurus.dinosauruski.model.Slot;

import java.util.List;

@Service
public interface TeacherService {


    List<Slot> findAllFreeSlotsByTeacherId(Long teacherId);
}
