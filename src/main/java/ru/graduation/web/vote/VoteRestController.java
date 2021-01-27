package ru.graduation.web.vote;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = ru.graduation.web.vote.VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController extends AbstractVoteController {

    static final String REST_URL = "/rest/profile/votes";

    @Override
    @PostMapping("/{restaurantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vote(@PathVariable int restaurantId) {
        super.vote(restaurantId);
    }
}

