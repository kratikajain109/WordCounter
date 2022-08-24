package com.synechron.word;

import java.util.List;

public interface WordCounterService {

    public void addWords(List<String> words) throws WordCounterConditionNotMetException;

    public int countOfWord(String word) ;
}
