package com.sommelier.wine4you.service.impl;

import com.sommelier.wine4you.exception.ResourceNotFoundException;
import com.sommelier.wine4you.model.WineStyle;
import com.sommelier.wine4you.repository.WineStyleRepository;
import com.sommelier.wine4you.service.WineStyleService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class WineStyleServiceImplTest {
    private final static Long fakeId = 35L;
    private final static Long testId = 5L;
    private final static String testWineStyleName = "Sweet";
    private final static String anotherTestWineStyleName = "Dry";
    private final WineStyle testStyle = new WineStyle();
    private final WineStyle style = new WineStyle();
    private WineStyleRepository wineStyleRepository;
    private WineStyleService wineStyleService;

    @BeforeEach
    void setUp() {
        wineStyleRepository = Mockito.spy(WineStyleRepository.class);
        wineStyleService = new WineStyleServiceImpl(wineStyleRepository);
        testStyle.setId(testId);
        testStyle.setNameStyle(testWineStyleName);
    }

    @Test
    void createWineStyle_Ok() {
        style.setNameStyle(testWineStyleName);
        Mockito.when(wineStyleRepository.save(style)).thenReturn(testStyle);
        WineStyle actual = wineStyleService.create(style);
        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.getId());
        Assertions.assertEquals(testWineStyleName, actual.getNameStyle());
    }

    @Test
    void getWineStyleById_Ok() {
        Mockito.when(wineStyleRepository.findById(testId)).thenReturn(Optional.of(testStyle));
        WineStyle actual = wineStyleService.getById(testId);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(testId, actual.getId());
        Assertions.assertEquals(testWineStyleName, actual.getNameStyle());
    }

    @Test
    void getWineStyleById_NotFound_Not_Ok() {
        try {
            wineStyleService.getById(fakeId);
        } catch (RuntimeException e) {
            Assertions.assertEquals("WineStyle not found with id : '"
                    + fakeId + "'", e.getMessage());
        }
    }

    @Test
    void getAllWineStyles_Ok() {
        Mockito.when(wineStyleRepository.findAll()).thenReturn(List.of(testStyle));
        List<WineStyle> actual = wineStyleService.getAll();
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(actual.size(), 1);
        Assertions.assertEquals(testId, actual.get(0).getId());
        Assertions.assertEquals(testWineStyleName, actual.get(0).getNameStyle());
    }

    @Test
    void deleteWineStyleById_Ok() {
        Assertions.assertFalse(wineStyleService.deleteById(testId));
    }

    @Test
    void deleteWineStyleById_NotFound_NotOk() {
        try {
            wineStyleService.deleteById(fakeId);
        } catch (ResourceNotFoundException e) {
            Assertions.assertEquals("WineStyle id 35", e.getMessage());
        }
    }

    @Test
    void findWineStyleByName_Ok() {
        Mockito.when(wineStyleRepository.findByNameStyle(testWineStyleName))
                .thenReturn(Optional.of(testStyle));
        WineStyle actual = wineStyleService.findByName(testWineStyleName);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(testId, actual.getId());
        Assertions.assertEquals(testWineStyleName, actual.getNameStyle());
    }

    @Test
    void findWineStyleByName_NotFound_NotOk() {
        try {
            wineStyleService.findByName("Fake");
        } catch (RuntimeException e) {
            Assertions.assertEquals("WineStyle not found with Style : 'Fake'"
                    , e.getMessage());
        }
    }

    @Test
    void updateWineStyle_Ok() {
        style.setId(testId);
        style.setNameStyle(anotherTestWineStyleName);
        testStyle.setNameStyle(anotherTestWineStyleName);
        Mockito.when(wineStyleRepository.save(testStyle)).thenReturn(style);
        WineStyle actual = wineStyleService.update(testId, testStyle);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(testId, actual.getId());
        Assertions.assertEquals(anotherTestWineStyleName, actual.getNameStyle());
    }
}