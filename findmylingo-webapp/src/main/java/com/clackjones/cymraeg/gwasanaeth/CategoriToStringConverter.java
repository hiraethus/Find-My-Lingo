package com.clackjones.cymraeg.gwasanaeth;

import org.springframework.stereotype.Service;
import org.springframework.core.convert.converter.Converter;

@Service
public class CategoriToStringConverter implements Converter<Categori, String> {
    @Override
    public String convert(Categori categori) {
        return categori.getId().toString();
    }
}
