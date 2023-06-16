package ch.ffhs.library.library.service.impl;

import ch.ffhs.library.library.model.CartItem;
import ch.ffhs.library.library.model.Customer;
import ch.ffhs.library.library.model.Product;
import ch.ffhs.library.library.model.ShoppingCart;
import ch.ffhs.library.library.repository.CartItemRepository;
import ch.ffhs.library.library.repository.ShoppingCartRepository;
import ch.ffhs.library.library.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private CartItemRepository itemRepository;

    @Autowired
    private ShoppingCartRepository cartRepository;

    @Override
    public ShoppingCart addItemToCart(Product product, int quantity, Customer customer) {
        ShoppingCart cart = customer.getShoppingCart();

        if(cart == null){
            cart = new ShoppingCart();
        }
        Set<CartItem> cartItems = cart.getCartItem();
        CartItem cartItem = findCartItem(cartItems, product.getId());
        if(cartItems == null){
            cartItems = new HashSet<>();
            if(cartItem == null) {
                cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setTotalPrice(quantity * product.getPrice());
                cartItem.setQuantity(quantity);
                cartItem.setCart(cart);
                cartItems.add(cartItem);
                itemRepository.save(cartItem);
            }
            } else {
                if(cartItems == null) {
                    cartItem = new CartItem();
                    cartItem.setProduct(product);
                    cartItem.setTotalPrice(quantity * product.getPrice());
                    cartItem.setQuantity(quantity);
                    cartItem.setCart(cart);
                    cartItems.add(cartItem);
                    itemRepository.save(cartItem);
                }
                else {
                    cartItem.setQuantity(cartItem.getQuantity() + quantity);
                    cartItem.setTotalPrice(cart.getTotalPrice() + (quantity * product.getPrice()));
                    itemRepository.save(cartItem);
                }
            }
        cart.setCartItem(cartItems);

        int totalItems = totalItems(cart.getCartItem());
        double totalPrice = totalPrice(cart.getCartItem());

        cart.setTotalPrice(totalPrice);
        cart.setTotalItems(totalItems);
        cart.setCustomer(customer);

        return cartRepository.save(cart);
    }

    @Override
    public ShoppingCart updateItemCart(Product product, int quantity, Customer customer) {
        ShoppingCart cart = customer.getShoppingCart();
        Set<CartItem> cartItems = cart.getCartItem();
        CartItem item = findCartItem(cartItems, product.getId());

        item.setQuantity(quantity);
        item.setTotalPrice(quantity * product.getPrice());
        itemRepository.save(item);

        int totalItems = totalItems(cartItems);
        double totalPrice = totalPrice(cartItems);

        cart.setTotalItems(totalItems);
        cart.setTotalPrice(totalPrice);


        return cartRepository.save(cart);
    }

    @Override
    public ShoppingCart deleteItemFromCart(Product product, Customer customer) {
        ShoppingCart cart = customer.getShoppingCart();
        Set<CartItem> cartItems = cart.getCartItem();
        CartItem item = findCartItem(cartItems, product.getId());
        cartItems.remove(item);
        itemRepository.delete(item);

        double totalPrice = totalPrice(cartItems);
        int totalItems = totalItems(cartItems);

        cart.setCartItem(cartItems);
        cart.setTotalPrice(totalPrice);
        cart.setTotalItems(totalItems);

        return cartRepository.save(cart);
    }

    private CartItem findCartItem(Set<CartItem> cartItems, Long productId){
        if(cartItems == null){
            return null;
        }
        CartItem cartItem = null;
        for(CartItem item : cartItems){
            if(item.getProduct().getId() == productId){
                cartItem = item;
            }
        }
        return cartItem;
    }

    private int totalItems(Set<CartItem> cartItems){
        int totalItems = 0;
        for(CartItem item : cartItems){
            totalItems += item.getQuantity();
        }
        return totalItems;
    }

    private double totalPrice(Set<CartItem> cartItems){
        double totalPrice = 0.0;

        for(CartItem item: cartItems){
            totalPrice += item.getTotalPrice();
        }
        return totalPrice;
    }

}
