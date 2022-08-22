package com.synechron.wordCounter.service.translator;

import org.springframework.stereotype.Service;

@Service
public interface TranslatorService {
    public String translate(String word);
}
