package com.clackjones.cymraeg.gwasanaeth;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.hamcrest.CoreMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class ServiceServiceTest {

    @Mock
    private SylwToSylwEntityMapper sylwToEntity;
    @Mock
    private SylwEntityToSylwMapper entityToSylw;
    @Mock
    private SylwDao sylwDao;
    @Mock
    private GwasanaethDao gwasanaethDao;
    @Mock
    private GwasanaethToGwasanaethEntityMapper gwasanaethToEntity;
    @Mock
    private GwasanaethEntityToGwasanaethMapper entityToGwasanaeth;

    @InjectMocks
    private GwasanaethService serviceService;

    @Test
    public void shouldCalculateAZServiceNames() {
        // given
        List<String> uniqueLetters = new ArrayList<>();
        uniqueLetters.add("A");
        uniqueLetters.add("B");
        uniqueLetters.add("D");

        given(gwasanaethDao.findUniqueFirstCharacters()).willReturn(uniqueLetters);

        // when
        List<String> serviceAZLetters = serviceService.calculateAZServiceNames();

        // then
        assertThat(serviceAZLetters, is(uniqueLetters));
    }
}
