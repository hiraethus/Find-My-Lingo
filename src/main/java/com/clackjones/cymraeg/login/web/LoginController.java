package com.clackjones.cymraeg.login.web;

import com.clackjones.cymraeg.user.RegistrationDetails;
import com.clackjones.cymraeg.user.RegistrationException;
import com.clackjones.cymraeg.user.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/mewngofnodi")
public class LoginController {

    @Autowired
    private RegistrationService registrationService;

    @Resource
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView loginForm(@ModelAttribute("registrationDetails") RegistrationDetails registrationDetails,
                                  RedirectAttributes attr) {
        ModelAndView modelAndView = new ModelAndView("loginForm");
        modelAndView.addObject(registrationDetails);

        return modelAndView;
    }

    @RequestMapping(path = "/cofrestru", method= RequestMethod.POST)
    public ModelAndView registerEndUser(@ModelAttribute("registrationDetails") RegistrationDetails registrationDetails,
                                        RedirectAttributes attr, Locale locale) {
        try {
            registrationService.register(registrationDetails);
        } catch (RegistrationException e) {
            attr.addFlashAttribute("registrationError", e.getKind());

            RegistrationDetails registrationDetailsNoPass = new RegistrationDetails();
            registrationDetailsNoPass.setUsername(registrationDetails.getUsername());
            attr.addFlashAttribute("registrationDetails", registrationDetailsNoPass);

            return new ModelAndView ("redirect:/");
        }

        String regSuccessfulMessage = messageSource.getMessage("registration.successful",
                new Object[]{registrationDetails.getUsername()}, locale);
        attr.addFlashAttribute("registrationSuccessful", regSuccessfulMessage);
        return new ModelAndView ("redirect:/");
    }

    @RequestMapping(path = "/ailosod", method = RequestMethod.GET)
    public ModelAndView resetPasswordInitialScreen(
            @ModelAttribute("registrationDetails") RegistrationDetails registrationDetails, RedirectAttributes attr) {
        Map<String, ?> flashAttributes = attr.getFlashAttributes();
        ModelAndView modelView = new ModelAndView("resetPassword");
        modelView.addObject(registrationDetails);

        return modelView;
    }

    @RequestMapping(path = "/ailosod", method = RequestMethod.POST)
    public ModelAndView resetPasswordSubmitRequest(@ModelAttribute("registrationDetails") RegistrationDetails registrationDetails,
                                                   RedirectAttributes attr, Locale locale) {
        String email = registrationDetails.getUsername();
        if (!registrationService.userExists(email)) {
            String emailNotFound = messageSource.getMessage("registration.email.not.found",
                    null, locale);
            attr.addFlashAttribute("emailNotFoundError", emailNotFound);
            return new ModelAndView ("redirect:ailosod");
        }

        registrationService.sendResetToken(registrationDetails, locale);

        return new ModelAndView ("checkEmail");
    }

    @RequestMapping(path = "/ailosod/tocyn/{token}", method = RequestMethod.GET)
    public ModelAndView enterNewPassword(@PathVariable("token") String token,
                                         @ModelAttribute("registrationDetails") RegistrationDetails registrationDetails) {
        ModelAndView resetPasswordView =  new ModelAndView("enterNewPassword");
        resetPasswordView.addObject("token", token);

        return resetPasswordView;
    }

    @RequestMapping(path = "/ailosod/tocyn/{token}", method = RequestMethod.POST)
    public ModelAndView submitNewPassword(@PathVariable("token") String token,
                                         @ModelAttribute("registrationDetails") RegistrationDetails registrationDetails,
                                          RedirectAttributes attr) {
        try {
            registrationService.resetPassword(registrationDetails, token);
        } catch(RegistrationException exc) {
            attr.addFlashAttribute("registrationException", exc.getKind());
            return new ModelAndView("redirect:/mewngofnodi/ailosod/tocyn/{token}");
        }
        return new ModelAndView("passwordChangedSuccessfully");
    }
}
