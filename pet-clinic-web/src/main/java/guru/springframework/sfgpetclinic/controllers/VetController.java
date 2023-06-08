package guru.springframework.sfgpetclinic.controllers;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping("/vets")
public class VetController {

    @RequestMapping({"","/","/index","/index.html"})
    public String listVets(){
        return "vets/index";
    }
}
