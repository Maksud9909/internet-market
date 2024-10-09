package uz.ccrew.internetmarket.controller;

import uz.ccrew.internetmarket.dto.Response;
import uz.ccrew.internetmarket.dto.ResponseMaker;
import uz.ccrew.internetmarket.service.impl.PaymentServiceImpl;

import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Payment Controller", description = "Payment API")
public class PaymentController {

    private final PaymentServiceImpl paymentServiceImpl;

    @GetMapping("/pay")
    @Operation(summary = "Pay for the order")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public ResponseEntity<Response<?>> pay() {
        paymentServiceImpl.pay();
        return ResponseMaker.okMessage("Order has been successfully paid");
    }
}
