package com.clackjones.cymraeg.login.web;

import com.clackjones.cymraeg.registration.RegistrationDetails;
import com.clackjones.cymraeg.registration.RegistrationException;
import com.clackjones.cymraeg.registration.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/mewngofnodi")
public class LoginController {

    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView loginForm(@ModelAttribute("registrationDetails") RegistrationDetails registrationDetails,
                                  RedirectAttributes attr) {
        ModelAndView modelAndView = new ModelAndView("loginForm");
        modelAndView.addObject(registrationDetails);

        return modelAndView;
    }

    @RequestMapping(path = "/cofrestru", method= RequestMethod.POST)
    public ModelAndView registerEndUser(@ModelAttribute("registrationDetails") RegistrationDetails registrationDetails,
                                        RedirectAttributes attr) {
        try {
            registrationService.register(registrationDetails);
        } catch (RegistrationException e) {
            attr.addFlashAttribute("registrationError", e.getKind());

            RegistrationDetails registrationDetailsNoPass = new RegistrationDetails();
            registrationDetailsNoPass.setUsername(registrationDetails.getUsername());
            attr.addFlashAttribute("registrationDetails", registrationDetailsNoPass);

            return new ModelAndView ("redirect:/mewngofnodi");
        }

        attr.addFlashAttribute("registrationSuccessful", String.format("Cofrestrwyd %s yn llwyddianus! Cewch mewngofnodi nawr.", registrationDetails.getUsername()));
        return new ModelAndView ("redirect:/gwasanaethau/");
    }

}
