package vlad.kucher.spribe.service;

import org.springframework.stereotype.Service;
import vlad.kucher.spribe.model.Player;
import vlad.kucher.spribe.util.exception.OpponentNotFoundException;

import java.util.NavigableSet;
import java.util.concurrent.ConcurrentSkipListSet;

import static vlad.kucher.spribe.util.PlayerUtil.checkPlayer;

/**
 * Created by Vlad on 07.11.2017.
 */
@Service
public class FindOpponentService {

    private NavigableSet<Player> players = new ConcurrentSkipListSet<>(
            (p1, p2) ->
            Double.compare(p1.getRating(), p2.getRating()) == 0 ?
            p1.getName().compareTo(p2.getName()) : Double.compare(p1.getRating(), p2.getRating())
    );

    public void addPlayer(Player player){
        checkPlayer(player);
        players.add(player);
    }

    public Player findOpponent(Player player) throws OpponentNotFoundException {
        checkPlayer(player);

        Player lower = players.lower(player);
        Player higher = players.higher(player);

        Player opponent;
        if (lower != null && higher != null) {
            opponent = player.getRating()-lower.getRating() < higher.getRating()-player.getRating() ? lower : higher;
        } else if (lower != null) {
            opponent = lower;
        } else if (higher != null) {
            opponent = higher;
        } else {
            throw new OpponentNotFoundException("Opponent not found");
        }

        if (!players.remove(opponent)){
            return findOpponent(player);
        }

        return opponent;
    }

    public void clear(){
        players.clear();
    }

    public boolean contains(Player player){
        return players.contains(player);
    }
}
