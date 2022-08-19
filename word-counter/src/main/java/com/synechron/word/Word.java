package com.synechron.word;

import java.util.Objects;
import java.util.Set;

public class Word {

    private int counter;
    private Set<String> originalWord;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Set<String> getOriginalWord() {
        return originalWord;
    }

    public void setOriginalWord(Set<String> originalWord) {
        this.originalWord = originalWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;
        Word word = (Word) o;
        return getCounter() == word.getCounter() && Objects.equals(getOriginalWord(), word.getOriginalWord());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCounter(), getOriginalWord());
    }
}
