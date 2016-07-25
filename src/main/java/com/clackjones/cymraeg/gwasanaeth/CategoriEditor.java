package com.clackjones.cymraeg.gwasanaeth;

import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class CategoriEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) {
        Long categoriId = Long.parseLong(text);
        Categori categori = new Categori();
        categori.setId(categoriId);
        setValue(categori);
    }
}
