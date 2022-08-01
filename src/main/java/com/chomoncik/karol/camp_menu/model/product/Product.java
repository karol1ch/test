package com.chomoncik.karol.camp_menu.model.product;

import com.chomoncik.karol.camp_menu.model.Ingredient;
import com.chomoncik.karol.camp_menu.model.ProductType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Getter
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", unique = true)
    private String name;

    @Column(name = "calories")
    private Integer calories;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_type")
    ProductType productType;

    @OneToOne(mappedBy = "product")
    private Ingredient ingredient;


    public Product() {
        this.id = 0L;
        this.name = null;
        this.calories = null;
        this.productType = null;
        this.ingredient = null;
    }

    public Product(ProductRequestDTO productRequestDTO) {
        this.id = 0L;
        this.name = productRequestDTO.getName();
        this.calories = productRequestDTO.getCalories();
        this.productType = productRequestDTO.getProductType();
        this.ingredient = null;
    }
}
