package ch.ffhs.customer;

import ch.ffhs.customer.controller.ProductController;
import ch.ffhs.library.library.dto.ProductDto;
import ch.ffhs.library.library.model.Category;
import ch.ffhs.library.library.service.ProductService;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;
    private ProductDto product, product1, product2;

    private List<ProductDto> productList = new ArrayList<>();

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        Category category = new Category("category");
        product = new ProductDto(1L, "product", "test", 1.0, 10, category, "image", true);
        product1 = new ProductDto(2L, "product1", "test", 20.0, 10, category, "image", true);
        product2 = new ProductDto(3L, "product2", "test", 65.0, 10, category, "image", true);

        productList.add(product);
        productList.add(product1);
        productList.add(product2);

        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @AfterEach
    void tearDown() {
        product = null;
        product1 = null;
        product2 = null;
        productList = null;
    }

    @Test
    void GetMappingOfProducts() throws Exception {

        when(productService.findAll()).thenReturn(productList);
        mockMvc.perform(get("/products").contentType(MediaType.APPLICATION_JSON).content(asJsonString(product))).andDo(print()).andExpect(status().isOk());
        verify(productService).findAll();
        verify(productService, times(1)).findAll();
    }


    @Test
    void GetMappingOfProductByID() throws Exception {

        when(productService.getById(any())).thenReturn(product);
        mockMvc.perform(get("/products/1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(product))).andDo(print()).andExpect(status().isOk());
        verify(productService).getById(any());
        verify(productService, times(1)).getById(any());
    }

    /**
    @Test
    void GetMappingOfFilterHigh() throws Exception {


        Collections.reverse(productList);
        when(productService.filterHighPrice()).thenReturn(productList);
        mockMvc.perform(get("/high-price").contentType(MediaType.APPLICATION_JSON).content(asJsonString(product))).andDo(print()).andExpect(status().isOk());
        verify(productService).filterHighPrice();
        verify(productService, times(1)).filterHighPrice();
    }

    @Test
    void GetMappingOfFilterLow() throws Exception {

        when(productService.filterLowPrice()).thenReturn(productList);
        mockMvc.perform(get("/low-price").contentType(MediaType.APPLICATION_JSON).content(asJsonString(product))).andDo(print()).andExpect(status().isOk());
        verify(productService).filterLowPrice();
        verify(productService, times(1)).filterLowPrice();
    }
    **/
    @Test
    void PostMappingOfProduct() throws Exception {

        when(productService.save(any())).thenReturn(product);
        mockMvc.perform(post("/product").contentType(MediaType.APPLICATION_JSON).content(asJsonString(product))).andDo(print()).andExpect(status().isCreated());

        verify(productService).save(any());
        verify(productService, times(1)).save(any());
    }

    public static String asJsonString(final ProductDto product) {
        try {
            return new ObjectMapper().writeValueAsString(product);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
