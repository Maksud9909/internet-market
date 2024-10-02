package uz.ccrew.internetmarket.controller;

import uz.ccrew.internetmarket.enums.Status;
import uz.ccrew.internetmarket.dto.Response;
import uz.ccrew.internetmarket.dto.ResponseMaker;
import uz.ccrew.internetmarket.dto.order.OrderDTO;
import uz.ccrew.internetmarket.service.OrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Order Controller", description = "Order API")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/create/order")
    @Operation(summary = "Create an order")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public ResponseEntity<Response<OrderDTO>> create() {
        OrderDTO result = orderService.create();
        return ResponseMaker.ok(result);
    }

    @GetMapping("/get/order/{id}")
    @Operation(summary = "Get an order")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public ResponseEntity<Response<OrderDTO>> getById(@PathVariable("id") Long id) {
        OrderDTO result = orderService.getById(id);
        return ResponseMaker.ok(result);
    }

    @PatchMapping("/change/status/{id}")
    @Operation(summary = "Change status of order for Administrator")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
    public ResponseEntity<Response<?>> changeStatus(@PathVariable("id") Long id, Status status) {
        orderService.changeStatus(id, status);
        return ResponseMaker.okMessage("Order status has been changed");
    }

    @GetMapping("/get/list")
    @Operation(summary = "Get list of orders")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public ResponseEntity<Response<Page<OrderDTO>>> getList(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                            @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
        Page<OrderDTO> result = orderService.getList(page, size);
        return ResponseMaker.ok(result);
    }
}
