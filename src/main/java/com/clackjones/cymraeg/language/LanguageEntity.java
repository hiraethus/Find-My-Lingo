package com.clackjones.cymraeg.language;

import javax.persistence.*;

@Entity
@Table(name="LanguageEntity")
@NamedQueries({
        @NamedQuery(name = "LanguageEntity.findAll",
                query = "SELECT l FROM LanguageEntity l")
})
public class LanguageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="native_lang_name", nullable = false)
    private String nativeLangName;

    public Long getId() {
        return id;
    }

    public String getNativeLangName() {
        return nativeLangName;
    }

    public void setNativeLangName(String nativeLangName) {
        this.nativeLangName = nativeLangName;
    }
}
