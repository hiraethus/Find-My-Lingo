package com.clackjones.cymraeg.language;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class LanguageConversionService implements Converter<String, Language> {
    private LanguageService languageService;

    @Autowired
    public LanguageConversionService(LanguageService languageService) {
        this.languageService = languageService;
    }

    @Override
    public Language convert(String s) {
        Long langId = Long.parseLong(s);
        return languageService.listAllLanguages().stream()
                .filter(lang -> lang.getId().equals(langId))
                .findFirst()
                .orElse(null);
    }
}
