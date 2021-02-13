package pl.dinosaurus.dinosauruski.slot;

import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.dinosaurus.dinosauruski.model.CurrentUser;
import pl.dinosaurus.dinosauruski.model.Slot;
import pl.dinosaurus.dinosauruski.model.Teacher;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.List;

@Controller
@Secured("ROLE_TEACHER")
@AllArgsConstructor
public class SlotController {

    private final SlotService slotService;

    @GetMapping("/teacher/slots/new")
    public String getNewSlotForm(Model model) {
        model.addAttribute("slot", new FreeSlotDto());
        return "teacher/slots/new";
    }

    @PostMapping("/teacher/slots/new")
    public String createNewSlot(@AuthenticationPrincipal CurrentUser currentUser,
                                @Valid @ModelAttribute("slot") FreeSlotDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return "teacher/slots/new";
        }
        @NotNull LocalTime time = dto.getTime();
        @NotNull DAY_OF_WEEK dayOfWeek = dto.getDayOfWeek();

        Slot slot = new Slot();
        slot.setTime(time);
        slot.setDayOfWeek(dayOfWeek);
        slot.setTeacher((Teacher) currentUser.getUser());
        slotService.saveNewSlot(slot);
        return "redirect:/teacher/slots/all";
    }

    @GetMapping("/teacher/slots/all")
    public String getAllSlots(@AuthenticationPrincipal CurrentUser currentUser,
                              Model model) {
        List<Slot> slots = slotService.findAllSlotsByTeacherId(currentUser.getUser().getId());
        model.addAttribute("slots", slots);
        return "teacher/slots/all";
    }

    @GetMapping("/teacher/slots/edit/{id}")
    public String editForm(Model model, @PathVariable("id") Long slotId) {
        Slot slot = slotService.findByIdOrThrow(slotId);
        if (slot.isBooked()) {
            ////////
            return "teacher/slots/booked/edit";
        }
        FreeSlotDto dto = new FreeSlotDto(slotId, slot.getDayOfWeek(), slot.getTime());
        model.addAttribute("slot", dto);
        return "teacher/slots/free/edit";
    }

    @PostMapping("/teacher/slots/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long slotId,
                       @ModelAttribute("slot") FreeSlotDto dto) {
        Slot slot = slotService.findByIdOrThrow(slotId);
        if (slot.isBooked()) {
            ///////////
        } else if (!slot.isBooked()) {
            slot.setDayOfWeek(dto.getDayOfWeek());
            slot.setTime(dto.getTime());
        }
        slotService.update(slot);
        return "redirect:/teacher/slots/all";
    }

    @GetMapping("/teacher/slots/delete/{id}")
    public String confirmDeleting(Model model, @PathVariable("id") Long slotId) {
        Slot slot = slotService.findByIdOrThrow(slotId);
        if (slot.isBooked()) {
            ////////
            return "teacher/slots/booked/delete";
        }
        FreeSlotDto dto = new FreeSlotDto(slotId, slot.getDayOfWeek(), slot.getTime());
        model.addAttribute("slot", dto);
        return "teacher/slots/free/delete";
    }

    @PostMapping("/teacher/slots/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long slotId,
                         @ModelAttribute("slot") FreeSlotDto dto) {
        Slot slot = slotService.findByIdOrThrow(slotId);
        if (slot.isBooked()) {
            ///////////
        }
        slotService.delete(slot);
        return "redirect:/teacher/slots/all";
    }
}
