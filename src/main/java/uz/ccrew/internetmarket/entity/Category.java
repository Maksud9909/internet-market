package uz.ccrew.internetmarket.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Category extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;
    private String name;
    private String description;
}
