package com.chomoncik.karol.camp_menu.controller;

import com.chomoncik.karol.camp_menu.model.product.ProductRequestDTO;
import com.chomoncik.karol.camp_menu.model.product.ProductResponseDTO;
import com.chomoncik.karol.camp_menu.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        if (productService.checkIfProductWithNameExist(productRequestDTO.getName())) {
            return new ResponseEntity<>("Product with given name exist", CONFLICT);
        } else {
            ProductResponseDTO productResponseDTO = productService.createProduct(productRequestDTO);
            return new ResponseEntity<>(productResponseDTO, CREATED);
        }
    }

    @DeleteMapping(path = "{product_name}")
    public ResponseEntity<?> deleteProduct(@PathVariable String product_name) {
        if (!productService.checkIfProductWithNameExist(product_name)) {
            return new ResponseEntity<>("Product with name " + product_name +" not exist", NOT_FOUND);
        } else {
            productService.deleteProductByName(product_name);
            return new ResponseEntity<>(NO_CONTENT);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        List<ProductResponseDTO> productResponseDTOList = productService.getAllProducts();
        return new ResponseEntity<>(productResponseDTOList, OK);
    }

    @GetMapping(path = "{product_name}")
    public ResponseEntity<?> getProductByName(@PathVariable String product_name) {
        ProductResponseDTO productResponseDTO = productService.getProductByName(product_name);
        return new ResponseEntity<>(productResponseDTO, OK);
    }
}
