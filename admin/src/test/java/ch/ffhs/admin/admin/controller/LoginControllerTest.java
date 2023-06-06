package ch.ffhs.admin.admin.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;



@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {
    /**

    @Autowired
    private LoginController controller;

    @Test
    void contextLoads() throws Exception{
        assertThat(controller).isNotNull();
    }
     **/
    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenUrl_whenGetRequest_thenFindGetResponse() throws Exception{
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/login");
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().string("Login");

        this.mockMvc.perform(builder).andExpect(contentMatcher).andExpect(MockMvcResultMatchers.status().isOk());
    }

}