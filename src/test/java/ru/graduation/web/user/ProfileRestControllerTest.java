package ru.graduation.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.graduation.UserTestData;
import ru.graduation.repository.UserRepository;
import ru.graduation.web.AbstractControllerTest;
import ru.graduation.web.json.JsonUtil;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.graduation.TestUtil.userHttpBasic;
import static ru.graduation.UserTestData.USER_ID;
import static ru.graduation.UserTestData.USER_MATCHER;
import static ru.graduation.web.user.ProfileRestController.REST_URL;

public class ProfileRestControllerTest extends AbstractControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(UserTestData.user)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(UserTestData.user));
    }

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL)
                .with(userHttpBasic(UserTestData.user)))
                .andExpect(status().isNoContent());
        USER_MATCHER.assertMatch(userRepository.getAll(), UserTestData.admin);
    }

    @Test
    void update() throws Exception {
        perform(MockMvcRequestBuilders.put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(UserTestData.user))
                .content(JsonUtil.writeValue(UserTestData.getUpdated())))
                .andDo(print())
                .andExpect(status().isNoContent());

        USER_MATCHER.assertMatch(userRepository.get(USER_ID), UserTestData.getUpdated());
    }
}
