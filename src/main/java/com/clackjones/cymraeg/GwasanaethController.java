package com.clackjones.cymraeg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import java.util.stream.Collectors;

@Controller
@RequestMapping("/gwasanaethau/*")
public class GwasanaethController {

    @Autowired
    private GwasanaethDao gwasanaethDao;

    @Autowired
    private GwasanaethEntityToGwasanaethMapper entityToGwasanaeth;

    @Autowired
    private GwasanaethToGwasanaethEntityMapper gwasanaethToEntity;

    @Autowired
    private CategoriDao categoriDao;

    @Autowired
    private CategoriEntityToCategoriMapper entityToCategori;

    @Autowired
    private CategoriEditor categoriEditor;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(new GwasanaethValidator());
        binder.registerCustomEditor(Categori.class, categoriEditor);
    }

    @RequestMapping(path = "ychwanegu", method = RequestMethod.GET)
    public ModelAndView addForm(Model model) {
        Collection<CategoriEntity> categoriEntities = categoriDao.findAll();
        List<Categori> categoris = categoriEntities.stream()
                                                    .map(entity -> entityToCategori.map(entity))
                                                    .collect(Collectors.toList());

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
        setCategoriForGwasanaethEntity(gwasanaethEntity, gwasanaeth);

        gwasanaethDao.persist(gwasanaethEntity);

        Long id = gwasanaethEntity.getId();

        return new ModelAndView("redirect:id/"+id);
    }

    private void setCategoriForGwasanaethEntity(GwasanaethEntity entity, Gwasanaeth gwasanaeth) {
        Categori categori = gwasanaeth.getCategori();
        if (categori != null) {
            // check to see if its in the database
            CategoriEntity categoriEntity = categoriDao.findById(categori.getId());
            if (categoriEntity != null) {
                entity.setCategori(categoriEntity);
            }
        }
    }

    @Transactional
    @RequestMapping(path = "id/{id}", method = RequestMethod.GET)
    public ModelAndView viewGwasanaeth(@PathVariable("id") Long id) {
        GwasanaethEntity gwasanaethEntity = gwasanaethDao.findById(id);
        Gwasanaeth gwasanaeth = entityToGwasanaeth.map(gwasanaethEntity);


        ModelAndView modelAndView = new ModelAndView("gweldGwasanaeth", "gwasanaeth", gwasanaeth);
        modelAndView.addObject("heading", gwasanaeth.getEnw());
        return modelAndView;
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "Methu canfod y wasanaeth hon")
    public void resourceNotFound () { }
}
