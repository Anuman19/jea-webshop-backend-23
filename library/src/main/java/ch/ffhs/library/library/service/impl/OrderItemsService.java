package ch.ffhs.library.library.service.impl;

import ch.ffhs.library.library.model.OrderItem;
import ch.ffhs.library.library.repository.OrderItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrderItemsService {

    @Autowired
    private OrderItemRepository orderItemsRepository;

    public void addOrderedProducts(OrderItem orderItem) {
        orderItemsRepository.save(orderItem);
    }

}