package vlad.kucher.spribe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vlad.kucher.spribe.model.Player;
import vlad.kucher.spribe.service.FindOpponentService;

/**
 * Created by Vlad on 07.11.2017.
 */
@RestController
@RequestMapping(FindOpponentController.REST_URL)
public class FindOpponentController {

    static final String REST_URL = "/rest/players";

    @Autowired
    private FindOpponentService service;

    @PutMapping
    public void addPlayer(@RequestBody Player player) {
        service.addPlayer(player);
    }

    @PostMapping
    public Player findOpponent(@RequestBody Player player){
        return service.findOpponent(player);
    }
}
