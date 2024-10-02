package uz.ccrew.internetmarket.controller;

import uz.ccrew.internetmarket.dto.Response;
import uz.ccrew.internetmarket.dto.ResponseMaker;
import uz.ccrew.internetmarket.dto.product.ProductDTO;
import uz.ccrew.internetmarket.service.ProductService;
import uz.ccrew.internetmarket.dto.product.ProductCreateDTO;
import uz.ccrew.internetmarket.dto.product.ProductUpdateDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Product Controller", description = "Product API")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/get/list")
    @Operation(summary = "Get all products")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CUSTOMER')")
    public ResponseEntity<Response<Page<ProductDTO>>> getList(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                              @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
        Page<ProductDTO> result = productService.getList(page, size);
        return ResponseMaker.ok(result);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Get product by id")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CUSTOMER')")
    public ResponseEntity<Response<ProductDTO>> get(@PathVariable("id") Long id) {
        ProductDTO product = productService.getById(id);
        return ResponseMaker.ok(product);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete product by id for Administrators")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
    public ResponseEntity<Response<?>> delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return ResponseMaker.okMessage("Product deleted successfully");
    }

    @PostMapping("/create/product")
    @Operation(summary = "Create product for Administrators")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
    public ResponseEntity<Response<ProductDTO>> create(@RequestBody ProductCreateDTO dto) {
        ProductDTO result = productService.create(dto);
        return ResponseMaker.ok(result);
    }

    @PatchMapping("/update/product/{id}")
    @Operation(summary = "Update product for Administrators")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
    public ResponseEntity<Response<ProductDTO>> update(@PathVariable("id") Long id, @RequestBody ProductUpdateDTO dto) {
        ProductDTO result = productService.update(id, dto);
        return ResponseMaker.ok(result);
    }
}
