package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }


    @InitBinder
    public void setAllowedFields(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");
    }


    @GetMapping("/{id}")
    public ModelAndView viewOwner(@PathVariable Long id){
        ModelAndView modelAndView=new ModelAndView("owners/ownerDetails");
        Owner owner=this.ownerService.findById(id);
        modelAndView.addObject("owner",owner);
        return  modelAndView;
    }

    @GetMapping("/find")
    public String findOwners(Model model){

        model.addAttribute("owner", new Owner());
        return "owners/findOwners";
    }

    @GetMapping("/search-owner-for")
    public String processFindOwners(Owner owner, BindingResult bindingResult, Model model){
        if(owner.getLastName()==null)owner.setLastName("");
        List<Owner> owners=this.ownerService.findAllByLastNameContains(owner.getLastName());
        if(owners.isEmpty()){
            bindingResult.rejectValue("lastName","notFound","not found");
            return "owners/findOwners";
        } else if(owners.size()==1){
            owner=owners.get(0);
            return "redirect:/owners/"+owner.getId();
        } else {
            model.addAttribute("owners", owners);
            return "owners/ownerList";
        }
    }

}
