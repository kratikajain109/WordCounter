package com.synechron.wordCounter.service;

import com.synechron.wordCounter.exception.WordCounterConditionNotMetException;
import com.synechron.wordCounter.service.translator.TranslatorService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class WordCounterService {

    private final Map<String, Integer> wordLibrary = new ConcurrentHashMap<String, Integer>();

    TranslatorService translatorService;
    private static final String RESILIENCE4J_INSTANCE_NAME = "wordCounter";
    private static final String FALLBACK_METHOD = "fallback";

    @Autowired
    public WordCounterService(TranslatorService translatorService) {
        this.translatorService = translatorService;
    }

    public void addWords(Collection<String> words) throws WordCounterConditionNotMetException {
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

    @CircuitBreaker(name = RESILIENCE4J_INSTANCE_NAME, fallbackMethod = FALLBACK_METHOD)
    public int countOfWord(String word) {
        return wordLibrary.get(translatorService.translate(word));
    }

    public ResponseEntity<Integer> fallback(Exception ex) {
        log.debug("FallBack executed");
        return new ResponseEntity<>(-1, HttpStatus.CHECKPOINT);
    }
}
