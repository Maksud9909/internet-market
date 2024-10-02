package uz.ccrew.internetmarket.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Product extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private String name;

    private String description;

    private Double price;
}
