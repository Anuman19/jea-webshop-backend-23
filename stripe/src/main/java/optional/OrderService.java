package ch.ffhs.library.library.service;

import ch.ffhs.library.library.model.ShoppingCart;

/**
 * The OrderService interface thus defines the basic
 * operations required for managing orders
 */
public interface OrderService {
    void saveOrder(ShoppingCart cart);

    void acceptOrder(Long id);

}
