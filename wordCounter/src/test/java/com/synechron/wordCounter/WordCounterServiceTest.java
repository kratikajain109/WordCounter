package com.synechron.wordCounter;

import com.synechron.wordCounter.exception.WordCounterConditionNotMetException;
import com.synechron.wordCounter.service.WordCounterService;
import com.synechron.wordCounter.service.translator.TranslatorService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class WordCounterServiceTest {
    WordCounterService wordCounter;

    @Mock
    TranslatorService translatorService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        wordCounter = new WordCounterService(translatorService);
    }

    @Test
    public void add_one_word_Test() {
        List<String> words = new ArrayList<String>() {{
            add("FLOWER");
        }};
        when(translatorService.translate("FLOWER")).thenReturn("FLOWER");
        wordCounter.addWords(words);
        Assert.assertEquals(1, wordCounter.countOfWord("FLOWER"));
    }

    @Test
    public void add_oneOrMore_word_Test() {
        List<String> words = new ArrayList<String>() {{
            add("FLOWER");
            add("MOVIE");
        }};
        when(translatorService.translate("FLOWER")).thenReturn("FLOWER");
        when(translatorService.translate("MOVIE")).thenReturn("MOVIE");
        wordCounter.addWords(words);
        Assert.assertEquals(1, wordCounter.countOfWord("FLOWER"));
        Assert.assertEquals(1, wordCounter.countOfWord("MOVIE"));
    }

    @Test
    public void add_oneOrMoreSame_word_Test() {
        List<String> words = new ArrayList<String>() {{
            add("FLOWER");
            add("FLOWER");
            add("MOVIE");
        }};
        when(translatorService.translate("FLOWER")).thenReturn("FLOWER");
        when(translatorService.translate("MOVIE")).thenReturn("MOVIE");
        wordCounter.addWords(words);
        Assert.assertEquals(2, wordCounter.countOfWord("FLOWER"));
        Assert.assertEquals(1, wordCounter.countOfWord("MOVIE"));
    }

    @Test
    public void add_NonAlphabetic_Word_Test() {
        List<String> words = new ArrayList<String>() {{
            add("ENTER?@");
        }};
        assertThrows(WordCounterConditionNotMetException.class,
                () -> wordCounter.addWords(words));
    }

    @Test
    public void add_differentLanguage_word_Test() {
        List<String> words = new ArrayList<String>() {{
            add("flower");
            add("flor");
            add("blume");
        }};
        when(translatorService.translate(any())).thenReturn("flower");
        wordCounter.addWords(words);
        Assert.assertEquals(3, wordCounter.countOfWord("flower"));
        Assert.assertEquals(3, wordCounter.countOfWord("flor"));
        Assert.assertEquals(3, wordCounter.countOfWord("blume"));
    }

}

