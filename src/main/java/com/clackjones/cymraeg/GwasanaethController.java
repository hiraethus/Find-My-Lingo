package com.clackjones.cymraeg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/gwasanaethau/*")
public class GwasanaethController {

    @Autowired
    private GwasanaethDao gwasanaethDao;

    @Autowired
    private GwasanaethEntityToGwasanaethMapper entityToGwasanaeth;

    @Autowired
    private GwasanaethToGwasanaethEntityMapper gwasanaethToEntity;

    @RequestMapping(path = "ychwanegu", method = RequestMethod.GET)
    public ModelAndView addForm() {
        Gwasanaeth gwasanaeth = new Gwasanaeth();

        return new ModelAndView("adioGwasanaeth", "gwasanaeth", gwasanaeth);
    }

    @Transactional
    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ModelAndView submitForm(@ModelAttribute Gwasanaeth gwasanaeth, Model model) {
        GwasanaethEntity gwasanaethEntity = gwasanaethToEntity.map(gwasanaeth);
        gwasanaethDao.persist(gwasanaethEntity);

        Long id = gwasanaethEntity.getId();

        return new ModelAndView("redirect:id/"+id);
    }

    @Transactional
    @RequestMapping(path = "id/{id}", method = RequestMethod.GET)
    public ModelAndView viewGwasanaeth(@PathVariable("id") Long id) {
        GwasanaethEntity gwasanaethEntity = gwasanaethDao.findById(id);

        Gwasanaeth gwasanaeth = entityToGwasanaeth.map(gwasanaethEntity);


        return new ModelAndView("gweldGwasanaeth", "gwasanaeth", gwasanaeth);
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "Methu canfod y wasanaeth hon")
    public void resourceNotFound () { }
}
