package synechron.word;

import com.synechron.translator.TranslatorService;
import com.synechron.word.WordCounterService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WordCounterServiceTest {

    WordCounterService wordCounter;

    @Mock
    TranslatorService translatorService;


    @Before
    public void setUp() {
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

    @Test(expected = com.synechron.word.WordCounterConditionNotMetException.class)
    public void add_NonAlphabetic_Word_Test() {
        List<String> words = new ArrayList<String>() {{
            add("ENTER?@");
        }};
        wordCounter.addWords(words);
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
