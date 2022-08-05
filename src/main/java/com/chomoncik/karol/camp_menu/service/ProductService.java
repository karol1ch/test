package com.chomoncik.karol.camp_menu.service;

import com.chomoncik.karol.camp_menu.exception.BadRequestException;
import com.chomoncik.karol.camp_menu.exception.ResourceNotFoundException;
import com.chomoncik.karol.camp_menu.model.product.Product;
import com.chomoncik.karol.camp_menu.model.product.ProductRequestDTO;
import com.chomoncik.karol.camp_menu.model.product.ProductResponseDTO;
import com.chomoncik.karol.camp_menu.repository.ProductRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.ReadOnlyFileSystemException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.chomoncik.karol.camp_menu.model.product.ProductResponseDTO.*;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponseDTO> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList.stream()
                .map(ProductResponseDTO::createProductResponseDTO)
                .collect(Collectors.toList());
    }

    public ProductResponseDTO getProductById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found product with id " + id));
        return createProductResponseDTO(product);
    }

    public ProductResponseDTO getProductByName(String name) {
        Product product = productRepository.findProductByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Not found product with name " + name));
        return createProductResponseDTO(product);
    }

    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        if (productRequestDTO.getCalories() < 0) {
            throw new BadRequestException("Calories must be plus");
        }
        Product product = productRepository.save(new Product(productRequestDTO));
        return createProductResponseDTO(product);
    }

    public boolean checkIfProductWithNameExist(String name) {
        Optional<Product> product = productRepository.findProductByName(name);
        return product.isPresent();
    }

    @Transactional
    public void deleteProductByName(String name) {
        productRepository.deleteProductByName(name);
    }

}
