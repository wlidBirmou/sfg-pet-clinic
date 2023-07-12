package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }


    @GetMapping({"","/","/index","/index.html"})
    public String listOwners(Model model){

        model.addAttribute("owners",this.ownerService.findAll());
        this.ownerService.findAll().forEach(System.out::println);
        return "owners/index";
    }

    @GetMapping("/find")
    public String findOwners(){
        return "notimplemented";
    }

    @GetMapping("/{id}")
    public ModelAndView findOwner(@PathVariable Long id){
        ModelAndView modelAndView=new ModelAndView("owners/ownerDetails");
        Owner owner=this.ownerService.findById(id);
        modelAndView.addObject("owner",owner);
        return  modelAndView;
    }

}
