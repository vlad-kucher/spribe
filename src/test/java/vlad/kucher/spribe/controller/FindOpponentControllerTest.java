package vlad.kucher.spribe.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import vlad.kucher.spribe.model.Player;
import vlad.kucher.spribe.service.FindOpponentService;
import vlad.kucher.spribe.util.json.JsonUtil;

import static org.junit.Assert.*;
import static vlad.kucher.spribe.TestData.PLAYER1;
import static vlad.kucher.spribe.TestData.PLAYER2;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static vlad.kucher.spribe.TestData.PLAYER3;

/**
 * Created by Vlad on 07.11.2017.
 */
public class FindOpponentControllerTest extends AbstractControllerTest {

    private static final String REST_URL = FindOpponentController.REST_URL;

    @Autowired
    private FindOpponentService service;

    @Before
    public void setUp() throws Exception {
        service.clear();
        service.addPlayer(PLAYER1);
        service.addPlayer(PLAYER2);
    }

    @Test
    public void testAddPlayer() throws Exception {
        mockMvc.perform(put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(PLAYER3)))
                .andExpect(status().isOk());

        assertTrue(service.contains(PLAYER3));
    }

    @Test
    public void testFindOpponent() throws Exception {
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(PLAYER3)))
                .andExpect(status().isOk());

        Player opponent = JsonUtil.readValue(action.andReturn().getResponse().getContentAsString(), Player.class);
        assertEquals(PLAYER1, opponent);
    }

}