package uz.ccrew.internetmarket.dto.product;

import lombok.Builder;

@Builder
public record ProductCreateDTO(Long categoryId,
                               String productName,
                               String description,
                               Double price) {}
