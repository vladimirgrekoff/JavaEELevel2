package com.grekoff.lesson7.core.tests;


import com.grekoff.lesson7.api.CartDto;
import com.grekoff.lesson7.api.CartItemDto;
import com.grekoff.lesson7.core.entities.Category;
import com.grekoff.lesson7.core.entities.Product;
import com.grekoff.lesson7.core.integrations.CartServiceIntegration;
import com.grekoff.lesson7.core.repositories.OrdersRepository;
import com.grekoff.lesson7.core.services.OrdersService;
import com.grekoff.lesson7.core.services.ProductsService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OrdersServiceTest {

    @Autowired
    private OrdersService ordersService;
    @MockBean
    private CartServiceIntegration cartServiceIntegration;

    @MockBean
    private ProductsService productsService;

    @MockBean
    private OrdersRepository ordersRepository;

    @Test
    public void createOrderTest() {
        CartDto cartDto = new CartDto();
        List<CartItemDto> items = new ArrayList<>();
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setProductTitle("Карамель");
        cartItemDto.setPricePerProduct(BigDecimal.valueOf(120));
        cartItemDto.setQuantity(2);
        cartItemDto.setPrice(BigDecimal.valueOf(240));
        cartItemDto.setProductId(100L);
        cartDto.setTotalPrice(BigDecimal.valueOf(240));
        cartDto.setItems(List.of(cartItemDto));

        Mockito.doReturn(cartDto).when(cartServiceIntegration).getCurrentCart("user");

        Category category = new Category();
        category.setId(1L);
        category.setTitle("Продукты");

        Product product = new Product();
        product.setId(100L);
        product.setPrice(BigDecimal.valueOf(120));
        product.setTitle("Карамель");
        product.setCategory(category);

        Mockito.doReturn(Optional.of(product)).when(productsService).findById(100L);

        ordersService.createOrder("user");

        Mockito.verify(ordersRepository, Mockito.times(1)).save(ArgumentMatchers.any());

    }
}
