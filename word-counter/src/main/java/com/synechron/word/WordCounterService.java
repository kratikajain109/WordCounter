package com.synechron.word;

import com.synechron.translator.TranslatorService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public interface WordCounterService {

    public void addWords(List<String> words) throws WordCounterConditionNotMetException;

    public int countOfWord(String word) ;
}
