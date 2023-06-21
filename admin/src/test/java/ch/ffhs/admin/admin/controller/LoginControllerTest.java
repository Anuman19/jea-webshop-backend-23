package ch.ffhs.admin.admin.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Java6Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = LoginController.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")

class LoginControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private LoginController controller;

    @Test
    void contextLoads() throws Exception{
        assertThat(controller).isNotNull();
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenUrl_whenGetRequest_thenFindGetResponse() throws Exception{
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/login");
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().string("Login");

        this.mockMvc.perform(builder).andExpect(contentMatcher).andExpect(MockMvcResultMatchers.status().isOk());
    }

}