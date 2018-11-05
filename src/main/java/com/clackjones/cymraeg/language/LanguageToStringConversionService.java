package com.clackjones.cymraeg.language;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class LanguageToStringConversionService implements Converter<Language, String> {
    @Override
    public String convert(Language language) {
        return language.getId().toString();
    }
}
