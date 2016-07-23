package com.clackjones.cymraeg;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mewngofnodi")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView loginForm() {

        ModelAndView modelAndView = new ModelAndView("loginForm");
        return modelAndView;
    }
}
