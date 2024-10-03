package uz.ccrew.internetmarket.mapper;

import uz.ccrew.internetmarket.entity.Product;
import uz.ccrew.internetmarket.entity.Category;
import uz.ccrew.internetmarket.dto.product.ProductDTO;
import uz.ccrew.internetmarket.dto.product.ProductCreateDTO;
import uz.ccrew.internetmarket.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper implements Mapper<ProductCreateDTO, ProductDTO, Product> {
    private final CategoryRepository categoryRepository;

    @Override
    public Product toEntity(ProductCreateDTO dto) {
        Category category = categoryRepository.loadById(dto.categoryId(),"Category not found");

        return Product.builder()
                .category(category)
                .productName(dto.productName())
                .description(dto.description())
                .price(dto.price())
                .build();
    }

    @Override
    public ProductDTO toDTO(Product product) {
        Category category = categoryRepository.loadById(product.getCategory().getCategoryId(),"Category not found");
        return ProductDTO.builder()
                .productId(product.getProductId())
                .categoryId(product.getCategory().getCategoryId())
                .categoryName(category.getCategoryName())
                .description(product.getDescription())
                .productName(product.getProductName())
                .price(product.getPrice())
                .build();
    }
}
