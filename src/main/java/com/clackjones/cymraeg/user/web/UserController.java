package com.clackjones.cymraeg.user.web;

import com.clackjones.cymraeg.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/defnyddiwr")
public class UserController {
    @Autowired
    private UserManager userManager;

    @RequestMapping(path = "/proffil", method = RequestMethod.GET)
    @Transactional
    public ModelAndView viewUserProfile(Principal principal) {
        // get user object
        if (principal.getName() == null) {
            // return permission denied
        }

        String username = principal.getName();
        User user = userManager.findUserByUsername(username);

        // display them
        ModelAndView view = new ModelAndView("proffil");
        view.addObject("gwasanaethau", user.getGwasanaethau());

        return view;
    }

}
