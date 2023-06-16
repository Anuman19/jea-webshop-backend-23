package ch.ffhs.library.library.service;

import ch.ffhs.library.library.model.Customer;
import ch.ffhs.library.library.model.Product;
import ch.ffhs.library.library.model.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart addItemToCart(Product product, int quantity, Customer customer);

    ShoppingCart updateItemCart(Product product, int quantity, Customer customer);

    ShoppingCart deleteItemFromCart(Product product, Customer customer);
}
