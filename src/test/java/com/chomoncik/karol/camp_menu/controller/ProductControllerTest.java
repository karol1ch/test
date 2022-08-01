package com.chomoncik.karol.camp_menu.controller;

import com.chomoncik.karol.camp_menu.model.product.Product;
import com.chomoncik.karol.camp_menu.model.product.ProductRequestDTO;
import com.chomoncik.karol.camp_menu.model.product.ProductResponseDTO;
import com.chomoncik.karol.camp_menu.repository.ProductRepository;
import com.chomoncik.karol.camp_menu.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static com.chomoncik.karol.camp_menu.model.ProductType.LIQUID;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    @BeforeEach
    private void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .build();
    }

    @Test
    void shouldGetProductByName() throws Exception {
        //GIVEN
        given(productService.getProductByName(PRODUCT_NAME)).willReturn(PRODUCT_RESPONSE_DTO);

        //WHEN
        MockHttpServletResponse response = mockMvc.perform(
                        get("/products/{product_name}", PRODUCT_NAME)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        //THEN
        assertThat(response.getStatus()).isEqualTo(OK.value());
        assertThat(response.getContentAsString()).contains(PRODUCT_NAME);
        assertThat(response.getContentAsString()).contains(String.valueOf(CALORIES));
        assertThat(response.getContentAsString()).contains(PRODUCT_TYPE);
    }

    @Test
    void shouldGetAllProducts() throws Exception {
        //GIVEN
        given(productService.getAllProducts()).willReturn(List.of(PRODUCT_RESPONSE_DTO));

        //WHEN
        MockHttpServletResponse response = mockMvc.perform(
                        get("/products")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        //THEN
        assertThat(response.getStatus()).isEqualTo(OK.value());
        assertThat(response.getContentAsString()).contains(PRODUCT_NAME);
        assertThat(response.getContentAsString()).contains(String.valueOf(CALORIES));
        assertThat(response.getContentAsString()).contains(PRODUCT_TYPE);
    }

    @Test
    void shouldNotDeleteProductWhenNotExist() throws Exception {
        //GIVEN
        given(productService.checkIfProductWithNameExist(PRODUCT_NAME)).willReturn(false);

        //WHEN
        MockHttpServletResponse response = mockMvc.perform(
                delete("/products/{product_name}", PRODUCT_NAME)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        //THEN
        assertThat(response.getStatus()).isEqualTo(NOT_FOUND.value());
        assertThat(response.getContentAsString()).contains("Product with name milk not exist");
    }

    @Test
    void shouldAddProduct() throws Exception {
        //GIVEN
        given(productService.checkIfProductWithNameExist(PRODUCT_NAME)).willReturn(false);
        given(productService.createProduct(PRODUCT_REQUEST_DTO)).willReturn(PRODUCT_RESPONSE_DTO);
        ObjectMapper mapper = new ObjectMapper();

        //WHEN
        MockHttpServletResponse response = mockMvc.perform(
                        post("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(PRODUCT_REQUEST_DTO))
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        //THEN
        AssertionsForClassTypes.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        AssertionsForClassTypes.assertThat(response.getContentAsString()).contains(PRODUCT_ID.toString());
        AssertionsForClassTypes.assertThat(response.getContentAsString()).contains(PRODUCT_NAME);
    }

    private static final Long PRODUCT_ID = 1L;

    private static final String PRODUCT_NAME = "milk";

    private static final int CALORIES = 100;

    private static final String PRODUCT_TYPE = LIQUID.name();

    private static final ProductResponseDTO PRODUCT_RESPONSE_DTO = ProductResponseDTO.builder()
            .id(1L)
            .name("milk")
            .calories(100)
            .productType(LIQUID.name())
            .build();

    private static final ProductRequestDTO PRODUCT_REQUEST_DTO = ProductRequestDTO.builder()
            .name("milk")
            .calories(100)
            .productType(LIQUID)
            .build();

}