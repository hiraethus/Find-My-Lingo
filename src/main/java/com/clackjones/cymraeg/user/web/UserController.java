package com.clackjones.cymraeg.user.web;

import com.clackjones.cymraeg.gwasanaeth.Gwasanaeth;
import com.clackjones.cymraeg.gwasanaeth.GwasanaethEntity;
import com.clackjones.cymraeg.gwasanaeth.GwasanaethEntityToGwasanaethMapper;
import com.clackjones.cymraeg.user.UserDao;
import com.clackjones.cymraeg.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Collection;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/defnyddiwr")
public class UserController {

    // Extract to service
    @Autowired
    private UserDao userDao;

    @Autowired
    private GwasanaethEntityToGwasanaethMapper entityToGwasanaeth;

    @RequestMapping(path = "/proffil", method = RequestMethod.GET)
    @Transactional
    public ModelAndView viewUserProfile(Principal principal) {
        // get user object
        if (principal.getName() == null) {
            // return permission denied
        }

        String username = principal.getName();

        // get gwasanaethau
        UserEntity userEntity = userDao.findById(username);
        Collection<Gwasanaeth> myGwasanaethau =
                userEntity.getGwasanaethau().stream()
                    .map(entity -> entityToGwasanaeth.map(entity))
                    .collect(Collectors.toSet());

        // display them
        ModelAndView view = new ModelAndView("proffil");
        view.addObject("gwasanaethau", myGwasanaethau);

        return view;
    }

}
