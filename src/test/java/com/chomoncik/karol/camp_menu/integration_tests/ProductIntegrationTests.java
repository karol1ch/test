package com.chomoncik.karol.camp_menu.integration_tests;

import com.chomoncik.karol.camp_menu.controller.ProductController;
import com.chomoncik.karol.camp_menu.model.product.Product;
import com.chomoncik.karol.camp_menu.model.product.ProductResponseDTO;
import com.chomoncik.karol.camp_menu.repository.ProductRepository;
import com.chomoncik.karol.camp_menu.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static com.chomoncik.karol.camp_menu.model.ProductType.LIQUID;
import static org.mockito.BDDMockito.given;


@ContextConfiguration(classes = {ProductController.class, ProductService.class})
@WebMvcTest
@AutoConfigureMockMvc
public class ProductIntegrationTests {

//    private static final String PRODUCT_NAME = "milk";
//
//    private static final ProductResponseDTO PRODUCT_RESPONSE_DTO = ProductResponseDTO.builder()
//            .name("milk")
//            .calories(100)
//            .productType(LIQUID.name())
//            .build();
//
//    private static final Product PRODUCT = Product.builder()
//            .id(1L)
//            .name("milk")
//            .calories(100)
//            .productType(LIQUID)
//            .build();
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private ProductRepository productRepository;
//
//    @Test
//    void shouldTest() {
//        //GIVEN
//        given(productRepository.findProductByName(PRODUCT_NAME)).willReturn(Optional.ofNullable(PRODUCT));
//
//        //WHEN
//        MvcResult result = mockMvc.perform();
//
//        //THEN
//    }

}
