package com.synechron.word;

import com.synechron.translator.TranslatorService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WordCounterServiceImpl implements  WordCounterService{
    private final Map<String, Integer> wordLibrary = new ConcurrentHashMap<String, Integer>();

    private final TranslatorService translatorService;

    public WordCounterServiceImpl(TranslatorService translatorService) {
        this.translatorService = translatorService;
    }

    public void addWords(List<String> words) throws WordCounterConditionNotMetException {
        words.forEach(word -> {
            if (!validateWord(word)) {
                throw new WordCounterConditionNotMetException();
            }
            wordLibrary.merge(translatorService.translate(word), 1, Integer::sum);
        });
    }

    private boolean validateWord(String word) {
        return ((word != null) && (!word.isEmpty()) && (word.chars().allMatch(Character::isLetter)));
    }

    public int countOfWord(String word) {
        return wordLibrary.get(translatorService.translate(word));
    }
}
