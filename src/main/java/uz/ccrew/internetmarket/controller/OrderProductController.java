package uz.ccrew.internetmarket.controller;


import uz.ccrew.internetmarket.dto.Response;
import uz.ccrew.internetmarket.dto.ResponseMaker;
import uz.ccrew.internetmarket.service.OrderProductService;
import uz.ccrew.internetmarket.dto.orderProduct.OrderProductDTO;
import uz.ccrew.internetmarket.dto.orderProduct.OrderProductCreateDTO;

import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/orderProduct")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "OrderProduct Controller", description = "OrderProduct API")
public class OrderProductController {
    private final OrderProductService orderProductService;

    @PostMapping("/addProduct")
    @Operation(summary = "Add product to order")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public ResponseEntity<Response<OrderProductDTO>> addOrderProduct(@RequestBody OrderProductCreateDTO dto) {
        OrderProductDTO result = orderProductService.addProductToOrder(dto);
        return ResponseMaker.ok(result);
    }

    @DeleteMapping("/delete/{orderId}/{productId}")
    @Operation(summary = "Delete product from order")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public ResponseEntity<Response<?>> delete(@PathVariable("orderId") Long orderId, @PathVariable("productId") Long productId) {
        orderProductService.deleteProductById(orderId, productId);
        return ResponseMaker.okMessage("Product deleted successfully from order");
    }
}
