package com.geekbrains.springmarket;

import com.geekbrains.springmarket.controllers.CartController;
import com.geekbrains.springmarket.entites.Product;
import com.geekbrains.springmarket.services.ProductService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebMvcTest(CartController.class)
public class CartControllerTest {

        @Autowired
        private MockMvc mvc;
        @MockBean
        private ProductService productService;
        @Test
        public void test() throws Exception {
// определяем нужное поведение заглушки productService
            given(this.productService.getProductById(0L))
                    .willReturn(new Product());
            this.mvc.perform(get("cart")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }

