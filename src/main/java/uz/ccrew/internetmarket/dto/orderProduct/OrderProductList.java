package uz.ccrew.internetmarket.dto.orderProduct;

import uz.ccrew.internetmarket.dto.product.ProductDTO;

import lombok.Builder;

import java.util.List;

@Builder
public record OrderProductList(Long orderId,
                               List<ProductDTO> products) {}
