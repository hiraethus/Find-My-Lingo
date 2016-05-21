package com.clackjones.cymraeg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/gwasanaethau/*")
public class GwasanaethController {

    @Autowired
    private GwasanaethDao gwasanaethDao;

    @Autowired
    private GwasanaethEntityToGwasanaethMapper entityToGwasanaeth;

    @Autowired
    private GwasanaethToGwasanaethEntityMapper gwasanaethToEntity;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(new GwasanaethValidator());
    }

    @RequestMapping(path = "ychwanegu", method = RequestMethod.GET)
    public String addForm(Model model) {
        if (!model.containsAttribute("gwasanaeth")) { model.addAttribute("gwasanaeth", new Gwasanaeth()); }

        return "adioGwasanaeth";
    }

    @Transactional
    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ModelAndView submitForm(@Valid @ModelAttribute("gwasanaeth") Gwasanaeth gwasanaeth, BindingResult result,
                                   RedirectAttributes attr) {
        if (result.hasErrors()) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.gwasanaeth", result);
            attr.addFlashAttribute("gwasanaeth", gwasanaeth);
            return new ModelAndView("redirect:ychwanegu");
        }

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
