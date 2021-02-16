package pl.dinosaurus.dinosauruski.individualClass;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dinosaurus.dinosauruski.model.IndividualClass;

import java.util.Optional;

@Service
@AllArgsConstructor
public class IndividualClassServiceImpl implements IndividualClassService {

    private final IndividualClassRepository individualClassRepository;

    @Override
    public boolean existClassByTeacherAndStudent(Long teacherId, Long studentId) {
        Optional<IndividualClass> optional = individualClassRepository.findFirstByTeacherIdAndStudentId(teacherId, studentId);
        return optional.isPresent();
    }
}
