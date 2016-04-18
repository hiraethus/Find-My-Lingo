package com.clackjones.cymraeg;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/gwasanaethau/*")
public class GwasanaethController {

    @RequestMapping(path = "ychwanegu", method = RequestMethod.GET)
    public String addForm(Model model) {
        Gwasanaeth gwasanaeth = new Gwasanaeth();
        gwasanaeth.setEnw("Byron");

        model.addAttribute("gwasanaeth", gwasanaeth);

        return "adioGwasanaeth";
    }

    @RequestMapping(path = "/ychwanegu", method = RequestMethod.POST)
    public @ResponseBody String submitForm(@ModelAttribute Gwasanaeth gwasanaeth, Model model) {
        System.out.printf("Name : %s\n", gwasanaeth.getEnw());
        return "Submitted";
    }
}
