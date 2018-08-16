package com.clackjones.cymraeg.gwasanaeth;

import static org.hamcrest.core.IsEqual.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class CategoriEntityToCategoriMapperTest {

    @Test
    public void shouldConvertCategoriToString() {
        // given
        CategoriEntity categoriEntity = new CategoriEntity();
        categoriEntity.setId(118118l);
        categoriEntity.setCategori("My categori");
        categoriEntity.setCategoryImg("my_img.jpg");


        // when
        CategoriEntityToCategoriMapper entityToCategori = new CategoriEntityToCategoriMapper();
        Categori result = entityToCategori.map(categoriEntity);

        assertThat(result.getId(), equalTo(categoriEntity.getId()));
        assertThat(result.getCategori(), equalTo(categoriEntity.getCategori()));
        assertThat(result.getCategoriImg(), equalTo(categoriEntity.getCategoryImg()));
    }
}
