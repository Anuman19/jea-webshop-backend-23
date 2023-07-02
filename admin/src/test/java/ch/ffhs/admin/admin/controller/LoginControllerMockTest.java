package ch.ffhs.admin.admin.controller;

import ch.ffhs.library.library.dto.AdminDto;
import ch.ffhs.library.library.dto.LoginDto;
import ch.ffhs.library.library.model.Admin;
import ch.ffhs.library.library.model.Role;
import ch.ffhs.library.library.service.impl.AdminServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class LoginControllerMockTest {

    @Mock
    private AdminServiceImpl adminService;

    private Admin admin;
    private AdminDto adminDto;
    private LoginDto loginDto;
    private Role role;

    @InjectMocks
    private LoginController loginController;

    private MockMvc mockMvc;

    /**
     * This is the setup for the testing. A role, admin, admindto and logindtos are created. The LoginController is injected.
     */
    @BeforeEach
    public void setup() {
        role = new Role(1L, "Admin");
        Collection<Role> collection = new ArrayList<>();
        collection.add(role);
        admin = new Admin(1L, "testAdmin", "testAdmin", "test", "test@mail", "password", collection);
        adminDto = new AdminDto(1L, "testAdmin", "testAdmin", "test", "test@mail", "password");
        loginDto = new LoginDto("testAdmin", "password");

        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    /**
     * After every test all the created objects are set to null.
     */
    @AfterEach
    void tearDown() {
        admin = null;
        adminDto = null;
        role = null;
        mockMvc = null;
    }

    /**
     * testing of POST /register and verify if a newly created admin is returned.
     */
    @Test
    void PostMappingOfRegister() throws Exception {

        when(adminService.save(any())).thenReturn(admin);
        mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON).content(asJsonString(adminDto))).andDo(print()).andExpect(status().isCreated());
        verify(adminService, times(2)).save(any());
    }

    /**
     * testing of POST /login and verify if the admin is returned.
     */
    @Test
    void PostMappingOfLogin() throws Exception {

        when(adminService.findByEmail(any())).thenReturn(admin);
        mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(asJsonString(loginDto))).andDo(print()).andExpect(status().isOk());
        verify(adminService, times(2)).findByEmail(any());

    }

    /**
     * helper methods
     */
    public static String asJsonString(final AdminDto adminDto) {
        try {
            return new ObjectMapper().writeValueAsString(adminDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String asJsonString(final LoginDto adminDto) {
        try {
            return new ObjectMapper().writeValueAsString(adminDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
