package ru.graduation.web.vote;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.graduation.model.Vote;

import java.util.List;

@RestController
@RequestMapping(value = VoteAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteAdminRestController extends AbstractVoteController {

    static final String REST_URL = "/rest/admin/votes";

    @Override
    @GetMapping("/{restaurantId}")
    public List<Vote> getTodayVotes(@PathVariable int restaurantId) {
        return super.getTodayVotes(restaurantId);
    }
}
