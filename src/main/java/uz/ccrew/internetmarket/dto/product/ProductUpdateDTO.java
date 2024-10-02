package uz.ccrew.internetmarket.dto.product;

import lombok.Builder;

@Builder
public record ProductUpdateDTO(String productName,
                               String description,
                               Double price) {}
