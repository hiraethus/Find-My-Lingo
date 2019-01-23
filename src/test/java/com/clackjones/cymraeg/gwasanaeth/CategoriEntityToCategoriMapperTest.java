package com.clackjones.cymraeg.gwasanaeth;

import static org.hamcrest.core.IsEqual.*;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class CategoriEntityToCategoriMapperTest {

    @Test
    public void shouldConvertCategoriToString() {
        // given
        CategoriEntity categoriEntity = new CategoriEntity();
        categoriEntity.setId(118118l);
        categoriEntity.setCategori("My categori");


        // when
        CategoriEntityToCategoriMapper entityToCategori = new CategoriEntityToCategoriMapper();
        Categori result = entityToCategori.map(categoriEntity);

        assertThat(result.getId(), equalTo(categoriEntity.getId()));
        assertThat(result.getCategori(), equalTo(categoriEntity.getCategori()));
    }

    @Test
    public void shouldSetCanDeleteToFalseIfCategoryHasAssociatedGwasanaethau() {
        // given
        CategoriEntity categoriEntity = new CategoriEntity();
        categoriEntity.setGwasanaethau(Arrays.asList(
                new GwasanaethEntity()
        ));

        // when
        CategoriEntityToCategoriMapper entityToCategori = new CategoriEntityToCategoriMapper();
        Categori result = entityToCategori.map(categoriEntity);


        // then
        assertThat(result.getCanDelete(), equalTo(false));
    }

    @Test
    public void shouldSetCanDeleteToTrueIfCategoryHasNoAssociatedGwasanaethau() {
        // given
        CategoriEntity categoriEntity = new CategoriEntity();
        categoriEntity.setGwasanaethau(Arrays.asList());

        // when
        CategoriEntityToCategoriMapper entityToCategori = new CategoriEntityToCategoriMapper();
        Categori result = entityToCategori.map(categoriEntity);

        // then
        assertThat(result.getCanDelete(), equalTo(true));
    }
}
