package com.clackjones.cymraeg;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class GwasanaethValidator implements Validator {

    private Pattern phoneRegex = Pattern.compile("\\d{5}\\s*\\d{6}");


    @Override
    public boolean supports(Class<?> clazz) {
        return Gwasanaeth.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors e) {
        Gwasanaeth gwasanaeth = (Gwasanaeth) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(e, "enw", "enw.is.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "ebost", "ebost.is.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "disgrifiad", "disgrifiad.is.required");

        if (!isRhifFfonValid(gwasanaeth.getRhifFfon())) {
            e.rejectValue("rhifFfon", "invalid.rhifFfon");
        }
    }

    private boolean isRhifFfonValid(final String rhifFfon) {
        boolean isRhifFfonEmpty = rhifFfon != null && rhifFfon.trim().length() == 0;

        return isRhifFfonEmpty || phoneRegex.matcher(rhifFfon).matches();
    }
}
