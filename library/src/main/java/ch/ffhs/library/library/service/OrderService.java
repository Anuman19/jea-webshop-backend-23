package ch.ffhs.library.library.service;

import ch.ffhs.library.library.model.ShoppingCart;

public interface OrderService {
    void saveOrder(ShoppingCart cart);

    void acceptOrder(Long id);

}
