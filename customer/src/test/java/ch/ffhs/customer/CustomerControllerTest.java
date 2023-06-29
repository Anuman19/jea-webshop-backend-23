package ch.ffhs.customer;

import ch.ffhs.customer.controller.LoginController;
import ch.ffhs.library.library.dto.CustomerDto;
import ch.ffhs.library.library.service.CustomerService;
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
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;
    private CustomerDto customer, customer1;


    private List<CustomerDto> customerList = new ArrayList<>();
    @InjectMocks
    private LoginController accountController;

    //@Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        customer = new CustomerDto("fTest", "lTest", "uTest", "pass", "rpass");
        customer1 = new CustomerDto("fTest1", "lTest1", "uTest1", "pass", "rpass");
        customerList.add(customer);
        customerList.add(customer1);

        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @AfterEach
    void tearDown() {
        customer = null;
        customer1 = null;
        customerList = null;
    }

    @Test
    void PostMappingOfCustomer() throws Exception {

        when(customerService.save(any())).thenReturn(customer);
        mockMvc.perform(post("/addAccount").contentType(MediaType.APPLICATION_JSON).content(asJsonString(customer))).andExpect(status().isCreated());
        verify(customerService, times(1)).save(any());
    }

    @Test
    void GetMappingOfCustomer() throws Exception {

        when(customerService.findAllDto()).thenReturn(customerList);
        mockMvc.perform(get("/accountList").contentType(MediaType.APPLICATION_JSON).content(asJsonString(customer))).andDo(print());
        verify(customerService).findAllDto();
        verify(customerService, times(1)).findAllDto();
    }

    public static String asJsonString(final CustomerDto customer) {
        try {
            return new ObjectMapper().writeValueAsString(customer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
