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
    private SylwDao sylwDao;

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

    @Autowired
    private SylwToSylwEntityMapper sylwToEntity;

    @Autowired
    private SafonEditor safonEditor;

    @Autowired
    private SylwEntityToSylwMapper entityToSylw;

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
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView listAllGwasanaethau(@RequestParam Map<String, String> params) {

        final Collection<GwasanaethEntity> gwasanaethauEntities = gwasanaethDao.findAll();
        List<Gwasanaeth> gwasanaethau = gwasanaethauEntities.stream()
                .map(gwasanaethEntity -> entityToGwasanaeth.map(gwasanaethEntity))
                .sorted(Comparator.comparing(Gwasanaeth::getCyfeiriadDinas, String::compareToIgnoreCase)
                .thenComparing(Comparator.comparing(Gwasanaeth::getEnw, String::compareToIgnoreCase)))
                .collect(Collectors.toList());

        String dinas = params.getOrDefault("dinas", "popeth");
        if (!dinas.equals("popeth")) {
            gwasanaethau = gwasanaethau.stream()
                    .filter(g -> g.getCyfeiriadDinas().equals(dinas)).collect(Collectors.toList());
        }

        String categori = params.getOrDefault("categori", "popeth");
        if (!categori.equals("popeth")) {
            gwasanaethau = gwasanaethau.stream()
                    .filter(g -> g.getCategori().getCategori().equals(categori)).collect(Collectors.toList());
        }

        ModelAndView modelAndView = new ModelAndView("rhestrGwasanaeth", "gwasanaethau", gwasanaethau);

        modelAndView.addObject("heading", "Rhestr gwasanaethau");
        return modelAndView;
    }

    @Transactional
    @RequestMapping(path = "id/{id}", method = RequestMethod.GET)
    public ModelAndView viewGwasanaeth(@PathVariable("id") Long id) {
        GwasanaethEntity gwasanaethEntity = gwasanaethDao.findById(id);
        Gwasanaeth gwasanaeth = entityToGwasanaeth.map(gwasanaethEntity);

        ModelAndView modelAndView = new ModelAndView("gweldGwasanaeth", "gwasanaeth", gwasanaeth);
        modelAndView.addObject("heading", gwasanaeth.getEnw());
        modelAndView.addObject("sylw", new Sylw());
        modelAndView.addObject("safonnau", SafonEnum.values());

        return modelAndView;
    }

    @Transactional
    @RequestMapping(path = "cyflwynoSylw/{gwasanaethId}", method = RequestMethod.POST)
    public ModelAndView cyflwynoSylw(@ModelAttribute("sylw") Sylw sylw, @PathVariable("gwasanaethId") Long gwasanaethId) {
        GwasanaethEntity gwasanaethEntity = gwasanaethDao.findById(gwasanaethId);

        if (gwasanaethEntity == null) {
            // TODO add error - gwasanaeth doesn't exist
            throw new NullPointerException();
        }

        // TODO add validator for sylw
        SylwEntity sylwEntity = sylwToEntity.map(sylw);
        sylwEntity.setGwasanaeth(gwasanaethEntity);
        sylwDao.persist(sylwEntity);

        gwasanaethEntity.getSylwadau().add(sylwEntity);

        return new ModelAndView("redirect:/gwasanaethau/id/"+gwasanaethId);
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "Methu canfod y wasanaeth hon")
    public void resourceNotFound () { }
}
