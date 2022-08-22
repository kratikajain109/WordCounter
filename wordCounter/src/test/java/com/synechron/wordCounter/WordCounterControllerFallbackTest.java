package com.synechron.wordCounter;

import com.synechron.wordCounter.service.WordCounterService;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureWebTestClient
@ExtendWith(SpringExtension.class)
public class WordCounterControllerFallbackTest {

   @Autowired
    private WebTestClient webTestClient;
/*    @Autowired
    private WebApplicationContext context;*/
    MockMvc mockMvc;
    @Mock
    WordCounterService wordCounter;
    @RepeatedTest(10)
    public void test(RepetitionInfo repetitionInfo) {
       webTestClient.get()
                .uri("/wordCounter/getWordCount/{word}/", "Flower")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .isOk();
    }
}
