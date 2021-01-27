package ru.graduation.web.vote;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.graduation.UserTestData;
import ru.graduation.util.exception.OutOfTimeException;
import ru.graduation.web.AbstractControllerTest;

import java.time.LocalTime;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.graduation.RestaurantTestData.RESTAURANT1_ID;
import static ru.graduation.RestaurantTestData.RESTAURANT2_ID;
import static ru.graduation.TestUtil.userHttpBasic;

public class VoteRestControllerTest extends AbstractControllerTest {
    static final String REST_URL = VoteRestController.REST_URL + '/';

    @Test
    void vote() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL + RESTAURANT1_ID)
                .with(userHttpBasic(UserTestData.user)))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void changeVoteBeforeEnd() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL + RESTAURANT1_ID)
                .with(userHttpBasic(UserTestData.user)))
                .andExpect(status().isNoContent())
                .andDo(print());

        AbstractVoteController.NOW = LocalTime.of(10, 30);

        perform(MockMvcRequestBuilders.post(REST_URL + RESTAURANT2_ID)
                .with(userHttpBasic(UserTestData.user)))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void changeVoteAfterEnd() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL + RESTAURANT1_ID)
                .with(userHttpBasic(UserTestData.user)))
                .andExpect(status().isNoContent())
                .andDo(print());

        AbstractVoteController.NOW = LocalTime.of(11, 30);

        Assertions.assertThatThrownBy(() -> perform(MockMvcRequestBuilders.post(REST_URL + RESTAURANT2_ID)
                .with(userHttpBasic(UserTestData.user)))
                .andExpect(status().isInternalServerError()))
                .hasCause(new OutOfTimeException("Your vote can no longer be changed"));
    }
}
