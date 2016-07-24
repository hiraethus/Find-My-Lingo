package com.clackjones.cymraeg.gwasanaeth.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clackjones.cymraeg.*;

@RestController
@RequestMapping("/categoriau/*")
public class CategoriController {

    @Autowired
    private CategoriManager categoriManager;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Transactional
    public String categori(@RequestParam(value="name") String categoriName) {
        categoriManager.saveCategori(categoriName);

        return "success!";
    }
}
