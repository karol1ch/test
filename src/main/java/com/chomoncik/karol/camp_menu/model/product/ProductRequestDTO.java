package com.chomoncik.karol.camp_menu.model.product;

import com.chomoncik.karol.camp_menu.model.ProductType;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ProductRequestDTO {
    private String name;
    private Integer calories;
    private ProductType productType;

}
