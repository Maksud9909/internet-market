package uz.ccrew.internetmarket.service.impl;

import uz.ccrew.internetmarket.entity.Product;
import uz.ccrew.internetmarket.entity.Category;
import uz.ccrew.internetmarket.mapper.ProductMapper;
import uz.ccrew.internetmarket.exp.NotFoundException;
import uz.ccrew.internetmarket.dto.product.ProductDTO;
import uz.ccrew.internetmarket.service.ProductService;
import uz.ccrew.internetmarket.dto.product.ProductCreateDTO;
import uz.ccrew.internetmarket.dto.product.ProductUpdateDTO;
import uz.ccrew.internetmarket.repository.CategoryRepository;
import uz.ccrew.internetmarket.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Page<ProductDTO> getList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("productId").descending());

        Page<Product> pageObj = productRepository.findAll(pageable);

        List<Product> userList = pageObj.getContent();
        List<ProductDTO> dtoList = userList.stream().map(productMapper::toDTO).toList();

        return new PageImpl<>(dtoList, pageable, pageObj.getTotalElements());
    }

    @Override
    public ProductDTO getById(Long id) {
        Product product = productRepository.loadById(id, "Product not found exception");
        return productMapper.toDTO(product);
    }

    @Override
    public ProductDTO create(ProductCreateDTO dto) {
        Category category = categoryRepository.loadById(dto.categoryId(), "Category not found exception");

        Product product = Product.builder()
                .category(category)
                .productName(dto.productName())
                .description(dto.description())
                .price(dto.price())
                .build();

        productRepository.save(product);
        return productMapper.toDTO(product);
    }

    @Override
    public ProductDTO update(Long id, ProductUpdateDTO dto) {
        Product product = productRepository.loadById(id, "Product not found exception");
        product.setProductName(dto.productName());
        product.setDescription(dto.description());
        product.setPrice(dto.price());

        productRepository.save(product);
        return productMapper.toDTO(product);
    }

    @Override
    public void delete(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new NotFoundException("Product with id " + id + " not found");
        }
    }
}
