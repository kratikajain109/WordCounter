package com.synechron.wordCounter.service.translator;

import org.springframework.stereotype.Service;

@Service
public class TranslatorServiceImpl implements TranslatorService{
    /**
     * Just a dummy class
     * @param word
     * @return
     */
    @Override
    public String translate(String word) {
        return "Flower";
    }
}

