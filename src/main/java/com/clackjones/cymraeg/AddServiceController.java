package com.clackjones.cymraeg;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/*")
public class AddServiceController {

    @RequestMapping(value="/", method = RequestMethod.GET)
    @ResponseBody
    public String helloWorld() {
        return "Hello, world!";
    }
}
