package com.clackjones.cymraeg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/categoriau/*")
public class CategoriController {

    @Autowired
    private CategoriDao categoriDao;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Transactional
    public String categori(@RequestParam(value="name") String categoriName) {
        CategoriEntity categoriEntity = new CategoriEntity();
        categoriEntity.setCategori(categoriName);

        categoriDao.persist(categoriEntity);

        return "success!";
    }
}
