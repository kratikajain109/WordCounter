package com.synechron.wordCounter;

import com.synechron.wordCounter.controller.WordCounterController;
import com.synechron.wordCounter.service.WordCounterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WordCounterController.class)
public class WordCounterControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    WordCounterService wordCounterService;

    @Test
    public void getWordCountTest() throws Exception {
        when(wordCounterService.countOfWord("Flower")).thenReturn(1);
        mockMvc.perform(MockMvcRequestBuilders.get("/wordCounter/getWordCount/{word}/", "Flower"))
                .andExpect(status().isOk());
    }

    @Test
    public void addWordTest() throws Exception {
        List<String> words = new ArrayList<String>() {{
            add("FLOWER");
        }};
        doNothing().when(wordCounterService).addWords(any());
        mockMvc.perform(MockMvcRequestBuilders.post("/wordCounter/add/").contentType(MediaType.APPLICATION_JSON)
                .param("words",words.toString()))
               .andExpect(status().is2xxSuccessful());
    }
}