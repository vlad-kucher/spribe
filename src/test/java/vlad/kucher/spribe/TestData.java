package vlad.kucher.spribe;

import vlad.kucher.spribe.model.Player;

/**
 * Created by Vlad on 07.11.2017.
 */
public class TestData {
    public static final Player PLAYER1 = new Player("player1", 50);
    public static final Player PLAYER2 = new Player("player2", 100);
    public static final Player PLAYER3 = new Player("player3", 70);

    public static final String P1 = "{\"name\" : \"player1\", \"rating\" : 50}";
    public static final String P2 = "{\"name\" : \"player2\", \"rating\" : 100}";
    public static final String P3 = "{\"name\" : \"player3\", \"rating\" : 70}";

    public static final String WORD = "test";

    private TestData(){
    }
}
