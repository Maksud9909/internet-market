package uz.ccrew.internetmarket.service;

import uz.ccrew.internetmarket.enums.Status;
import uz.ccrew.internetmarket.dto.order.OrderDTO;

import org.springframework.data.domain.Page;

public interface OrderService {
    OrderDTO create();

    OrderDTO get();

    Page<OrderDTO> getList(int page, int size);

    void delete();

    void changeStatus(Long id, Status status);
}
