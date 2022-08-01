package com.chomoncik.karol.camp_menu.model.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class ProductResponseDTO {
    private Long id;
    private String name;
    private Integer calories;
    private String productType;

    public static ProductResponseDTO createProductResponseDTO(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .calories(product.getCalories())
                .productType(product.getProductType().name())
                .build();
    }
}
