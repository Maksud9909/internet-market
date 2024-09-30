package uz.ccrew.internetmarket.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "order_products")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class OrderProducts extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private Double price;
}
