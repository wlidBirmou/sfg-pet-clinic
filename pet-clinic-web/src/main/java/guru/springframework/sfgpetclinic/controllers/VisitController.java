package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import guru.springframework.sfgpetclinic.utilities.WebUtilities;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.util.Optional;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/owners/{ownerId}/pets/{petId}")
public class VisitController {

    private final VisitService visitService;
    private final PetService petService;

    @ModelAttribute("pet")
    private void populatePet(@PathVariable("petId") Long petId, Model model){
        model.addAttribute("pet",this.petService.findById(petId));
    }

    @InitBinder
    public void setDataBinder(WebDataBinder dataBinder){

        dataBinder.setDisallowedFields("id");
        dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text));
            }
        });
    }

    @GetMapping("/visits/new")
    public String initVisitCreationForm(Model model, Pet pet, @RequestHeader(HttpHeaders.REFERER) Optional<String> refererOptional){
        Visit visit=new Visit();
        model.addAttribute("visit",visit);
        if(refererOptional.isPresent()) model.addAttribute("referer", refererOptional.get());
        else model.addAttribute("referer", WebUtilities.WEB_BASE_URL+"/owners/find");
        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping("/visits/new")
    public String processVisitCreationForm(Pet pet, Visit visit,@PathVariable Long petId, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "pets/createOrUpdateVisitForm";
        else {
            pet.getVisits().add(visit);
            visit.setPet(pet);
            Pet savedPet=this.petService.save(pet);
            return "redirect:/owners/"+savedPet.getOwner().getId();
        }
    }

}
