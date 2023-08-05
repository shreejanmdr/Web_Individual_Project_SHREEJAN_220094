package com.system.shreejanEcommerce.entity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productsCarts")
public class ProductCart {
    @Id
    @SequenceGenerator(name = "productsCarts_seq_gen", sequenceName = "productsCarts_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "productsCarts_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_productId"))
    private Products products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_userId"))
    private User user;
    private String imageBase64;
}
