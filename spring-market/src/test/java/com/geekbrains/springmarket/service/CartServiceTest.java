package com.geekbrains.springmarket.service;

import com.geekbrains.springmarket.entites.Category;
import com.geekbrains.springmarket.entites.Product;
import com.geekbrains.springmarket.entites.SystemUser;
import com.geekbrains.springmarket.entites.User;
import com.geekbrains.springmarket.repositories.IProductRepository;
import com.geekbrains.springmarket.repositories.ProductRepository;
import com.geekbrains.springmarket.services.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private IProductRepository productRepository;
    @Test
    public void addProductFailTest() {
        Product product = new Product();
        product.setTitle("Bread");

        Mockito.doReturn(new Product())
                .when(productRepository)
                .findOneByTitle("Bread");

        boolean isCreated = productService.saveProduct(product);

        Assert.assertFalse(isCreated);


        Mockito.verify(productRepository, Mockito.times(0)).save(ArgumentMatchers.any(Product.class));
    }
}
