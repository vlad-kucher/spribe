package vlad.kucher.spribe.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;
import vlad.kucher.spribe.service.WordCounterService;
import vlad.kucher.spribe.util.json.JsonUtil;

import static org.junit.Assert.*;
import static vlad.kucher.spribe.TestData.WORD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Vlad on 07.11.2017.
 */
public class WordCounterControllerTest extends AbstractControllerTest {

    private static final String REST_URL = WordCounterController.REST_URL + '/';

    @Autowired
    private WordCounterService service;

    @Before
    public void setUp() throws Exception {
        service.clear();
    }

    @Test
    public void testAddWord() throws Exception {
        mockMvc.perform(put(REST_URL + WORD))
                .andExpect(status().isOk())
                .andDo(print());

        assertEquals(service.getWordCount(WORD), 1);
    }

    @Test
    public void testGetWordCount() throws Exception {
        service.addWord(WORD);

        ResultActions action = mockMvc.perform(get(REST_URL + WORD))
                .andExpect(status().isOk())
                .andDo(print());

        assertTrue(Integer.valueOf(action.andReturn().getResponse().getContentAsString()) == 1);
    }

    @Test
    public void testInvalidWord() throws Exception {
        mockMvc.perform(put(REST_URL + "1_%"))
                .andExpect(status().isUnprocessableEntity());
    }
}