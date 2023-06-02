package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexControllers{

    @RequestMapping({"","/","index","index.html"})
    public String index(){
        return "index";
    }
}
