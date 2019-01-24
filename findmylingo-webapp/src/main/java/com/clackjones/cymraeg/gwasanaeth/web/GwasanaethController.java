package com.clackjones.cymraeg.gwasanaeth.web;

import com.clackjones.cymraeg.InvalidUserException;
import com.clackjones.cymraeg.gwasanaeth.*;
import com.clackjones.cymraeg.language.LanguageService;
import com.clackjones.cymraeg.opengraph.OpenGraphDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
    private LanguageService languageService;

    @Autowired
    OpenGraphDataGenerator openGraphDataGenerator;

    @Resource(name = "messageSource")
    private MessageSource messageSource;

    @InitBinder("gwasanaeth")
    public void initBinder(WebDataBinder binder) {
        //TODO convert to CategoriConverter
        binder.registerCustomEditor(Categori.class, categoriEditor);
    }

    @InitBinder("sylw")
    public void initSylwBinder(WebDataBinder binder) {
        binder.registerCustomEditor(SafonEnum.class, safonEditor);
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
    public ModelAndView viewGwasanaeth(@PathVariable("id") Long id, Principal principal) throws InvalidUserException, ServiceDoesntExistException {
        String username = principal != null ? principal.getName() : null;
        Gwasanaeth gwasanaeth = gwasanaethService.retrieveService(id, username);

        ModelAndView modelAndView = new ModelAndView("viewService", "gwasanaeth", gwasanaeth);
        modelAndView.addObject("heading", gwasanaeth.getEnw());
        modelAndView.addObject("sylw", new Sylw());
        modelAndView.addObject("safonnau", SafonEnum.values());

        modelAndView.addObject("og",
                openGraphDataGenerator.serviceToOpenGraphData(gwasanaeth, username));

        return modelAndView;
    }

    @RequestMapping(path = "cyflwynoSylw/{gwasanaethId}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADD_COMMENT')")
    public ModelAndView cyflwynoSylw(@ModelAttribute("sylw") Sylw sylw, @PathVariable("gwasanaethId") Long gwasanaethId) {
        sylw.setAmserSylw(new Date());
        gwasanaethService.addSylwForGwasanaethWithId(gwasanaethId, sylw);

        return new ModelAndView("redirect:/id/"+gwasanaethId);
    }
}
