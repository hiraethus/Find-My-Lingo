package com.clackjones.cymraeg.user.web;

import com.clackjones.cymraeg.user.User;
import com.clackjones.cymraeg.user.UserDao;
import com.clackjones.cymraeg.user.UserEntity;
import com.clackjones.cymraeg.user.UserEntityToUserMapper;
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

    // Extract to service
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserEntityToUserMapper entityToUserMapper;

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
        User user = entityToUserMapper.map(userEntity);

        // display them
        ModelAndView view = new ModelAndView("proffil");
        view.addObject("gwasanaethau", user.getGwasanaethau());

        return view;
    }

}
