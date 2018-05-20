package com.clackjones.cymraeg.language;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageService {
    private LanguageDao languageDao;

    @Autowired
    public LanguageService(LanguageDao languageDao) {
        this.languageDao = languageDao;
    }

    @Transactional
    public List<Language> listAllLanguages() {
        Collection<LanguageEntity> langs = languageDao.findAll();
        return toLanguages(langs);
    }

    private List<Language> toLanguages(Collection<LanguageEntity> langEntities) {
        return langEntities.stream().map(langEntity -> {
            Language l = new Language();
            l.setId(langEntity.getId());
            l.setNativeLanguageName(langEntity.getNativeLangName());
            return l;
        }).collect(Collectors.toList());
    }
}
