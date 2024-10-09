package uz.ccrew.internetmarket.dto.product;

import lombok.Builder;

@Builder
public record ProductDTO(Long productId,
                         Long categoryId,
                         String categoryName,
                         String productName,
                         String description,
                         Long quantity,
                         Double price) {}
