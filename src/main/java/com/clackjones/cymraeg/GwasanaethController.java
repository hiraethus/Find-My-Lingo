package com.clackjones.cymraeg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/gwasanaethau/*")
public class GwasanaethController {

    @Autowired
    private GwasanaethDao gwasanaethDao;

    @RequestMapping(path = "ychwanegu", method = RequestMethod.GET)
    public String addForm(Model model) {
        Gwasanaeth gwasanaeth = new Gwasanaeth();
        gwasanaeth.setEnw("Byron");

        model.addAttribute("gwasanaeth", gwasanaeth);

        return "adioGwasanaeth";
    }

    @Transactional
    @RequestMapping(path = "/", method = RequestMethod.POST)
    public @ResponseBody String submitForm(@ModelAttribute Gwasanaeth gwasanaeth, Model model) {
        GwasanaethEntity gwasanaethEntity = new GwasanaethEntity();
        gwasanaethEntity.setEnw(gwasanaeth.getEnw());

        gwasanaethDao.persist(gwasanaethEntity);

        return gwasanaethEntity.getId().toString();
    }
}
