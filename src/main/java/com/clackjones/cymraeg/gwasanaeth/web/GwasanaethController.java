package com.clackjones.cymraeg.gwasanaeth.web;

import com.clackjones.cymraeg.gwasanaeth.*;
import com.clackjones.cymraeg.language.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.naming.NoPermissionException;
import javax.validation.Valid;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class GwasanaethController {

    @Autowired
    private CategoriManager categoriManager;

    @Autowired
    private CategoriEditor categoriEditor;

    @Autowired
    private SafonEditor safonEditor;

    @Autowired
    private GwasanaethService gwasanaethService;

    @Autowired
    private GwasanaethValidator gwasanaethValidator;

    @Autowired
    private LanguageService languageService;

    @Resource(name = "messageSource")
    private MessageSource messageSource;

    @InitBinder("gwasanaeth")
    public void initBinder(WebDataBinder binder) {
        //TODO convert to CategoriConverter
        binder.addValidators(gwasanaethValidator);
        binder.registerCustomEditor(Categori.class, categoriEditor);
    }

    @InitBinder("sylw")
    public void initSylwBinder(WebDataBinder binder) {
        binder.registerCustomEditor(SafonEnum.class, safonEditor);
    }

    @RequestMapping(path = "add", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_CREATE_GWASANAETH')")
    public ModelAndView addForm(Model model, Principal principal, Locale locale) {
        List<Categori> categoris = categoriManager.findAll();

        // for when incorrect details entered and we need to pass the
        // same gwasanaeth back in
        Gwasanaeth gwasanaeth;
        if (model.containsAttribute("gwasanaeth")) {
            gwasanaeth = (Gwasanaeth)model.asMap().get("gwasanaeth");
        } else {
            gwasanaeth = new Gwasanaeth();
            gwasanaeth.setEbost(principal.getName());
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("gwasanaeth", gwasanaeth);
        map.put("categoris", localizedCategoris(locale));
        map.put("languages", languageService.listAllLanguages());

        return new ModelAndView("adioGwasanaeth", map);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ModelAndView submitForm(@Valid @ModelAttribute("gwasanaeth") Gwasanaeth gwasanaeth, BindingResult result,
                                   RedirectAttributes attr, Principal principal) {
        if (result.hasErrors()) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.gwasanaeth", result);
            attr.addFlashAttribute("gwasanaeth", gwasanaeth);
            return new ModelAndView("redirect:add");
        }

        Long id = gwasanaethService.saveGwasanaeth(gwasanaeth, principal.getName());

        return new ModelAndView("redirect:id/"+id);
    }

    @RequestMapping(path = "/", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ROLE_CREATE_GWASANAETH')")
    public ModelAndView updateGwasanaeth(@Valid @ModelAttribute("gwasanaeth") Gwasanaeth gwasanaeth, BindingResult result,
                                   RedirectAttributes attr, Principal principal) {
        if (result.hasErrors()) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.gwasanaeth", result);
            attr.addFlashAttribute("gwasanaeth", gwasanaeth);
            return new ModelAndView("redirect:edit");
        }

        try {
            gwasanaethService.updateGwasanaeth(gwasanaeth, principal.getName());
        } catch (GwasanaethNotFound gwasanaethNotFound) {
            throw new NullPointerException(gwasanaethNotFound.getMessage());
        } catch (NoPermissionException e) {
            throw new AccessDeniedException(e.getMessage());
        }

        return new ModelAndView("redirect:id/"+gwasanaeth.getId());
    }


    @RequestMapping(path = "edit/{gwasanaethId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_CREATE_GWASANAETH')")
    public ModelAndView adolyguGwasanaeth(@PathVariable("gwasanaethId") Long gwasanaethId,
                                          Principal principal, Locale locale) {
        Gwasanaeth gwasanaeth = gwasanaethService.findById(gwasanaethId);

        String name = principal.getName();
        if (!gwasanaeth.getOwner().equals(name)) {
            throw new AccessDeniedException(
                    String.format("User %s doesn't have permission to modify this gwasanaeth",name));
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("gwasanaeth", gwasanaeth);
        map.put("categoris", localizedCategoris(locale));
        map.put("languages", languageService.listAllLanguages());

        return new ModelAndView("adolyguGwasanaeth", map);
    }

    private List<Categori> localizedCategoris(Locale locale) {
        return categoriManager.findAll().stream().map(c -> {
            Categori localizedCategori = new Categori();
            localizedCategori.setId(c.getId());
            String localizedCategoriName = messageSource.getMessage(c.getCategori(), null, locale);
            localizedCategori.setCategori(localizedCategoriName);

            return localizedCategori;
        }).collect(Collectors.toList());
    }

    @Transactional
    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public ModelAndView listAllGwasanaethau(@RequestParam Map<String, String> params) {
        List<Gwasanaeth> gwasanaethau =
                gwasanaethService.freeSearchByNameAndCity(
                        params.getOrDefault("searchTerm", null),
                        params.getOrDefault("dinas", null),
                    params.getOrDefault("categori", null));

        ModelAndView modelAndView = new ModelAndView("searchTable", "gwasanaethau", gwasanaethau);
        return modelAndView;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView frontPage() {
        return new ModelAndView("tudalenFlaen");
    }

    @RequestMapping(path = "id/{id}", method = RequestMethod.GET)
    public ModelAndView viewGwasanaeth(@PathVariable("id") Long id) {
        Gwasanaeth gwasanaeth = gwasanaethService.findById(id);

        ModelAndView modelAndView = new ModelAndView("gweldGwasanaeth", "gwasanaeth", gwasanaeth);
        modelAndView.addObject("heading", gwasanaeth.getEnw());
        modelAndView.addObject("sylw", new Sylw());
        modelAndView.addObject("safonnau", SafonEnum.values());

        return modelAndView;
    }

    @RequestMapping(path = "cyflwynoSylw/{gwasanaethId}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADD_COMMENT')")
    public ModelAndView cyflwynoSylw(@ModelAttribute("sylw") Sylw sylw, @PathVariable("gwasanaethId") Long gwasanaethId) {
        sylw.setAmserSylw(new Date());
        gwasanaethService.addSylwForGwasanaethWithId(gwasanaethId, sylw);

        return new ModelAndView("redirect:/id/"+gwasanaethId);
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "Methu canfod y wasanaeth hon")
    public void resourceNotFound () { }
}
