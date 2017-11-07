package vlad.kucher.spribe.util;

import vlad.kucher.spribe.model.Player;

import java.util.Objects;

/**
 * Created by Vlad on 07.11.2017.
 */
public class PlayerUtil {

    private PlayerUtil(){
    }

    public static void checkPlayer(Player player) {
        Objects.requireNonNull(player, "Player must not be null");
        Objects.requireNonNull(player.getName(), "Name must not be null");
        if (!Double.isFinite(player.getRating())) {
            throw new IllegalArgumentException("Invalid rating");
        }
        if (player.getName().isEmpty()) {
            throw new IllegalArgumentException("Name must not be empty");
        }
    }
}
