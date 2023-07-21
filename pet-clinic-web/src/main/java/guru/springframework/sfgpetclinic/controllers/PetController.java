package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.util.Set;

@Controller
@AllArgsConstructor
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private final PetService  petService;
    private final PetTypeService petTypeService;
    private final OwnerService ownerService;

    @ModelAttribute("types")
    public Set<PetType> populatePetTypes(){
        return this.petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId){
        return this.ownerService.findById(ownerId);
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


    @GetMapping("/pets/new")
    public String initCreationForm(Model model){
        Pet pet=new Pet();
        pet.setOwner((Owner)model.getAttribute("owner"));
        model.addAttribute("pet",pet);
        return "pets/createOrUpdatePetForm";
    }

    @PostMapping("/pets/new")
    public String processCreationForm(Pet pet,Owner owner, BindingResult bindingResult){
        if(bindingResult.hasErrors())return "pets/createOrUpdatePetForm";
        owner.getPets().add(pet);
        pet.setOwner(owner);
        this.ownerService.save(owner);
        return "redirect:/owners/"+owner.getId();
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(Model model,@PathVariable Long petId){
        Pet pet=this.petService.findById(petId);
        pet.setOwner((Owner)model.getAttribute("owner"));
        model.addAttribute("pet",pet);
        return "pets/createOrUpdatePetForm";
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(Pet pet,Owner owner, BindingResult bindingResult){
        if(bindingResult.hasErrors())return "pets/createOrUpdatePetForm";
        this.petService.updatePetInOwner(owner,pet);
        this.ownerService.save(owner);
        return "redirect:/owners/"+owner.getId();
    }

}
