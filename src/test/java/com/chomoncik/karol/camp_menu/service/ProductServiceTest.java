package com.chomoncik.karol.camp_menu.service;

import com.chomoncik.karol.camp_menu.exception.ResourceNotFoundException;
import com.chomoncik.karol.camp_menu.model.ProductType;
import com.chomoncik.karol.camp_menu.model.product.Product;
import com.chomoncik.karol.camp_menu.model.product.ProductResponseDTO;
import com.chomoncik.karol.camp_menu.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static com.chomoncik.karol.camp_menu.model.ProductType.LIQUID;
import static org.mockito.BDDMockito.*;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    private static final String PRODUCT_NAME = "milk";

    private static final ProductResponseDTO PRODUCT_RESPONSE_DTO = ProductResponseDTO.builder()
            .name("milk")
            .calories(100)
            .productType(LIQUID.name())
            .build();

    private static final Product PRODUCT = Product.builder()
            .id(1L)
            .name("milk")
            .calories(100)
            .productType(LIQUID)
            .build();

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldGetProductByName() {
        //GIVEN
        given(productRepository.findProductByName(PRODUCT_NAME)).willReturn(Optional.ofNullable(PRODUCT));

        //WHEN
        ProductResponseDTO productResponseDTO = productService.getProductByName(PRODUCT_NAME);

        //THEN
         assertThat(productResponseDTO).isEqualTo(PRODUCT_RESPONSE_DTO);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenProductNotExist() {
        //GIVEN
        given(productRepository.findProductByName(PRODUCT_NAME)).willReturn(Optional.empty());

        //WHEN + THEN
        assertThatThrownBy(() -> productService.getProductByName(PRODUCT_NAME)).isInstanceOf(ResourceNotFoundException.class);
    }
}