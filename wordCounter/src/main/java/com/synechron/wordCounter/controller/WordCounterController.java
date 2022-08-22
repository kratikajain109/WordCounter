package com.synechron.wordCounter.controller;

import com.synechron.wordCounter.service.WordCounterService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/wordCounter")
public class WordCounterController {
    WordCounterService wordCounterService;


    @Autowired
    WordCounterController(WordCounterService wordCounterService) {
        this.wordCounterService = wordCounterService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestParam List<String> words) {
        wordCounterService.addWords(words);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/getWordCount/{word}/")
    public ResponseEntity<Integer> getWordCount(@PathVariable String word) {
        return new ResponseEntity<>(wordCounterService.countOfWord(word),HttpStatus.OK);
    }

}
