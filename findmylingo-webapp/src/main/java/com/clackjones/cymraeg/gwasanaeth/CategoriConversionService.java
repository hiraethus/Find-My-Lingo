package com.clackjones.cymraeg.gwasanaeth;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class CategoriConversionService implements Converter<String, Categori> {
    private final CategoriManager categoriManager;

    public CategoriConversionService(CategoriManager categoriManager) {
        this.categoriManager = categoriManager;
    }

    @Override
    public Categori convert(String s) {
        Long id = Long.valueOf(s);

        return this.categoriManager.findAll().stream()
                .filter(categori -> categori.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
