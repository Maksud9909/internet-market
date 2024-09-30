package uz.ccrew.internetmarket.entity;

import uz.ccrew.internetmarket.enums.Status;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Order extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_price")
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private Status status;
}
