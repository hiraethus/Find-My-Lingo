package com.clackjones.cymraeg.gwasanaeth.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.*;

import com.clackjones.cymraeg.*;

@Controller
@RequestMapping("/gwasanaethau/*")
public class GwasanaethController {

    @Autowired
    private CategoriManager categoriManager;

    @Autowired
    private CategoriEditor categoriEditor;

    @Autowired
    private SafonEditor safonEditor;

    @Autowired
    private GwasanaethManager gwasanaethManager;

    @InitBinder("gwasanaeth")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(new GwasanaethValidator());
        binder.registerCustomEditor(Categori.class, categoriEditor);
    }

    @InitBinder("sylw")
    private void initSylwBinder(WebDataBinder binder) {
        binder.registerCustomEditor(SafonEnum.class, safonEditor);
    }

    @RequestMapping(path = "ychwanegu", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE_OWNER')")
    public ModelAndView addForm(Model model) {
        List<Categori> categoris = categoriManager.findAll();

        // for when incorrect details entered and we need to pass the
        // same gwasanaeth back in
        Gwasanaeth gwasanaeth;
        if (model.containsAttribute("gwasanaeth")) {
            gwasanaeth = (Gwasanaeth)model.asMap().get("gwasanaeth");
        } else {
            gwasanaeth = new Gwasanaeth();
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("gwasanaeth", gwasanaeth);
        map.put("categoris", categoris);

        map.put("heading", "Ychwanegu gwasanaeth");

        return new ModelAndView("adioGwasanaeth", map);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ModelAndView submitForm(@Valid @ModelAttribute("gwasanaeth") Gwasanaeth gwasanaeth, BindingResult result,
                                   RedirectAttributes attr) {
        if (result.hasErrors()) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.gwasanaeth", result);
            attr.addFlashAttribute("gwasanaeth", gwasanaeth);
            return new ModelAndView("redirect:ychwanegu");
        }

        Long id = gwasanaethManager.saveGwasanaeth(gwasanaeth);

        return new ModelAndView("redirect:id/"+id);
    }

    @Transactional
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView listAllGwasanaethau(@RequestParam Map<String, String> params) {

        List<Gwasanaeth> gwasanaethau =
                gwasanaethManager.findAllWithConditionsAlphabetically(params.getOrDefault("dinas", null),
                    params.getOrDefault("categori", null));

        ModelAndView modelAndView = new ModelAndView("rhestrGwasanaeth", "gwasanaethau", gwasanaethau);
        modelAndView.addObject("heading", "Rhestr gwasanaethau");
        return modelAndView;
    }

    @RequestMapping(path = "id/{id}", method = RequestMethod.GET)
    public ModelAndView viewGwasanaeth(@PathVariable("id") Long id) {
        Gwasanaeth gwasanaeth = gwasanaethManager.findById(id);

        ModelAndView modelAndView = new ModelAndView("gweldGwasanaeth", "gwasanaeth", gwasanaeth);
        modelAndView.addObject("heading", gwasanaeth.getEnw());
        modelAndView.addObject("sylw", new Sylw());
        modelAndView.addObject("safonnau", SafonEnum.values());

        return modelAndView;
    }

    @RequestMapping(path = "cyflwynoSylw/{gwasanaethId}", method = RequestMethod.POST)
    public ModelAndView cyflwynoSylw(@ModelAttribute("sylw") Sylw sylw, @PathVariable("gwasanaethId") Long gwasanaethId) {
        gwasanaethManager.addSylwForGwasanaethWithId(gwasanaethId, sylw);

        return new ModelAndView("redirect:/gwasanaethau/id/"+gwasanaethId);
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "Methu canfod y wasanaeth hon")
    public void resourceNotFound () { }
}
