package vlad.kucher.spribe.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import vlad.kucher.spribe.model.Player;
import vlad.kucher.spribe.util.exception.OpponentNotFoundException;

import static org.junit.Assert.*;
import static vlad.kucher.spribe.TestData.PLAYER1;
import static vlad.kucher.spribe.TestData.PLAYER2;
import static vlad.kucher.spribe.TestData.PLAYER3;

/**
 * Created by Vlad on 07.11.2017.
 */
public class FindOpponentServiceTest extends AbstractServiceTest {

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
        service.addPlayer(PLAYER3);
        assertTrue(service.contains(PLAYER3));
    }

    @Test
    public void testFindOpponent1() throws Exception {
        assertEquals(service.findOpponent(PLAYER3), PLAYER1);
        assertFalse(service.contains(PLAYER1));
    }

    @Test
    public void testFindOpponent2() throws Exception {
        assertEquals(service.findOpponent(new Player("name", Double.MIN_VALUE)), PLAYER1);
        assertFalse(service.contains(PLAYER1));
    }

    @Test
    public void testFindOpponent3() throws Exception {
        assertEquals(service.findOpponent(new Player("name", Double.MAX_VALUE)), PLAYER2);
        assertFalse(service.contains(PLAYER2));
    }

    @Test
    public void testOpponentNotFound() throws Exception {
        service.clear();

        thrown.expect(OpponentNotFoundException.class);
        thrown.expectMessage("Opponent not found");
        service.findOpponent(PLAYER3);
    }

    @Test
    public void testInvalidPlayer() throws Exception {
        Player newPlayer = new Player("player");
        newPlayer.setRating(Double.NaN);

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid rating");
        service.addPlayer(newPlayer);
    }

    @Test
    public void testNullPlayer() throws Exception {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Player must not be null");
        service.findOpponent(null);
    }
}