package pl.dinosaurus.dinosauruski.slot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dinosaurus.dinosauruski.model.Slot;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class SlotServiceTest {

    @Mock
    private SlotRepository slotRepository;

    @InjectMocks
    private SlotService slotService;

    @Spy
    Slot slot;

    @Test
    void shouldBeAbleSaveNewSlot() {
        //given
        Slot slot = new Slot();
        //when
        slotService.saveNewSlot(slot);
        //then
        verify(slotRepository).save(slot);
    }

    @Test
    void newlyCreatedSlotShouldHaveBookedFalse() {
        //given
        Slot slot = new Slot();
        //when
        slotService.saveNewSlot(slot);
        //then
        assertFalse(slot.isBooked());
    }

    @Test
    void newlyCreatedSlotShouldNotHaveStudent() {
        //given
        Slot slot = new Slot();
        //when
        slotService.saveNewSlot(slot);
        //then
        assertNull(slot.getRegularStudent());
    }

//    @Test
//    void shouldBeAbleGetAllSlotsByTeacherId() {
//        //given
//        //when
//        slotService.getAllSlotsByTeacherId(anyLong());
//        //then
//        verify(slotRepository).findAllByTeacherIdAndArchivedFalse(anyLong());
//        assertThat(slotRepository.findAllByTeacherIdAndArchivedFalse(anyLong()), contains(any(Slot.class)));
//    }
}
