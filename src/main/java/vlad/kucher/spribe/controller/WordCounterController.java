package vlad.kucher.spribe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vlad.kucher.spribe.service.WordCounterService;

/**
 * Created by Vlad on 07.11.2017.
 */
@RestController
@RequestMapping(WordCounterController.REST_URL)
public class WordCounterController {

    static final String REST_URL = "/rest/words";

    @Autowired
    private WordCounterService service;

    @PutMapping(value = "/{word}")
    public void addWord(@PathVariable("word") String word) {
        service.addWord(word);
    }

    @GetMapping(value = "/{word}")
    public int getWordCount(@PathVariable("word") String word){
        return service.getWordCount(word);
    }
}
