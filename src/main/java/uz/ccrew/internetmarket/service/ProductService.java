package uz.ccrew.internetmarket.service;

import org.springframework.data.domain.Page;
import uz.ccrew.internetmarket.dto.product.ProductCreateDTO;
import uz.ccrew.internetmarket.dto.product.ProductDTO;
import uz.ccrew.internetmarket.dto.product.ProductUpdateDTO;

public interface ProductService {
    Page<ProductDTO> getList(int page, int size);
    ProductDTO getById(Long id);
    ProductDTO create(ProductCreateDTO dto);
    ProductDTO update(Long id, ProductUpdateDTO dto);
    void delete(Long id);
}
