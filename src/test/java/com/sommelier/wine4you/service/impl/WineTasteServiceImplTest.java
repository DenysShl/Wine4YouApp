package com.sommelier.wine4you.service.impl;

import com.sommelier.wine4you.exception.ResourceNotFoundException;
import com.sommelier.wine4you.model.WineTaste;
import com.sommelier.wine4you.repository.WineTasteRepository;
import com.sommelier.wine4you.service.WineTasteService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class WineTasteServiceImplTest {
    private final static Long fakeId = 204L;
    private final static Long testId = 40L;
    private final static String testWineTasteName = "Green apple";
    private final static String anotherTestWineTasteName = "Apple";
    private final WineTaste testTaste = new WineTaste();
    private final WineTaste taste = new WineTaste();
    private WineTasteRepository wineTasteRepository;
    private WineTasteService wineTasteService;


    @BeforeEach
    void setUp() {
        wineTasteRepository = Mockito.spy(WineTasteRepository.class);
        wineTasteService = new WineTasteServiceImpl(wineTasteRepository);
        testTaste.setId(testId);
        testTaste.setNameTaste(testWineTasteName);
    }

    @Test
    void createWineTaste_Ok() {
        taste.setNameTaste(testWineTasteName);
        Mockito.when(wineTasteRepository.save(taste)).thenReturn(testTaste);
        WineTaste actual = wineTasteService.create(taste);
        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.getId());
        Assertions.assertEquals(testWineTasteName, actual.getNameTaste());
    }

    @Test
    void getWineTasteById_Ok() {
        Mockito.when(wineTasteRepository.findById(testId)).thenReturn(Optional.of(testTaste));
        WineTaste actual = wineTasteService.getById(testId);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(testId, actual.getId());
        Assertions.assertEquals(testWineTasteName, actual.getNameTaste());
    }

    @Test
    void getWineTasteById_NotFound_NotOk() {
        try {
            wineTasteService.getById(fakeId);
        } catch (RuntimeException e) {
            Assertions.assertEquals("WineTaste not found with id : '"
                    + fakeId + "'", e.getMessage());
        }
    }

    @Test
    void getAllWineTastes_Ok() {
        Mockito.when(wineTasteRepository.findAll()).thenReturn(List.of(testTaste));
        List<WineTaste> actual = wineTasteService.getAll();
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(actual.size(), 1);
        Assertions.assertEquals(testId, actual.get(0).getId());
        Assertions.assertEquals(testWineTasteName, actual.get(0).getNameTaste());
    }

    @Test
    void deleteWineTasteById_Ok() {
        Assertions.assertFalse(wineTasteService.deleteById(testId));
    }

    @Test
    void deleteWineTasteById_NotFound_NotOk() {
        try {
            wineTasteService.deleteById(fakeId);
        } catch (ResourceNotFoundException e) {
            Assertions.assertEquals("WineTaste id 204", e.getMessage());
        }
    }

    @Test
    void findWineTasteByName_Ok() {
        Mockito.when(wineTasteRepository.findByNameTaste(testWineTasteName))
                .thenReturn(Optional.of(testTaste));
        WineTaste actual = wineTasteService.findByName(testWineTasteName);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(testId, actual.getId());
        Assertions.assertEquals(testWineTasteName, actual.getNameTaste());
    }

    @Test
    void findByName_NotFound_NotOk() {
        try {
            wineTasteRepository.findByNameTaste("Fake");
        } catch (ResourceNotFoundException e) {
            Assertions.assertEquals("WineTaste Name Fake", e.getMessage());
        }
    }

    @Test
    void updateWineTaste_Ok() {
        taste.setId(testId);
        taste.setNameTaste(anotherTestWineTasteName);
        testTaste.setNameTaste(anotherTestWineTasteName);
        Mockito.when(wineTasteRepository.save(testTaste)).thenReturn(taste);
        WineTaste actual = wineTasteService.update(testId, testTaste);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(testId, actual.getId());
        Assertions.assertEquals(anotherTestWineTasteName, actual.getNameTaste());
    }
}
