package com.clackjones.cymraeg;

import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class SafonEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(SafonEnum.valueOf(text));
    }
}
