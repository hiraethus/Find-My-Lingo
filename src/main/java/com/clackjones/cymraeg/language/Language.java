package com.clackjones.cymraeg.language;

public class Language implements java.io.Serializable {
    private Long id;
    private String nativeLanguageName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNativeLanguageName() {
        return nativeLanguageName;
    }

    public void setNativeLanguageName(String nativeLanguageName) {
        this.nativeLanguageName = nativeLanguageName;
    }
}
