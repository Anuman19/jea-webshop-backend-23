package ch.ffhs.customer.controller;

import ch.ffhs.library.library.model.Customer;
import ch.ffhs.library.library.model.Product;
import ch.ffhs.library.library.model.ShoppingCart;
import ch.ffhs.library.library.service.CustomerService;
import ch.ffhs.library.library.service.ProductService;
import ch.ffhs.library.library.service.ShoppingCartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * CartController, which is responsible for processing requests related to shopping carts
 */
@Controller
public class CartController {
    // injects service for customer management
    @Autowired
    private CustomerService customerService;

    // injects service for shopping cart management
    @Autowired
    private ShoppingCartService cartService;

    // injects service for product management
    @Autowired
    private ProductService productService;

    /**
     * method is called when an HTTP GET request is sent to the /cart URL
     *
     * @param model represents the data which is used to pass data to the view
     * @param principal represents the current logged-in user
     * @param session HttpSession object
     * @return String with view's name "cart.html"
     */
    @GetMapping("/cart")
    public String cart(Model model, Principal principal, HttpSession session){
        if(principal == null){
            return "redirect:/login";
        }
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);
        ShoppingCart shoppingCart = customer.getShoppingCart();
        if(shoppingCart == null){
            model.addAttribute("check", "No item in your cart");
        }
        session.setAttribute("totalItems", shoppingCart.getTotalItems());
        model.addAttribute("subTotal",shoppingCart.getTotalPrice());
        model.addAttribute("shoppingCart", shoppingCart);
        return "cart";
    }

    /**
     * method is called when an HTTP POST request is sent to the /add-to-cart URL
     *
     * @param productId of the product
     * @param quantity of the product
     * @param principal represents the current logged-in user
     * @param request HttpServletRequest
     * @return String with redirected pages
     */
    @PostMapping("/add-to-cart")
    public String addItemToCart(@RequestParam("id") Long productId,
                                @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity,
                                Principal principal, HttpServletRequest request){
        if(principal == null){
            return "redirect:/login";
        }
        Product product = productService.getProductById(productId);
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);

        ShoppingCart cart = cartService.addItemToCart(product, quantity, customer);
        return "redirect:" + request.getHeader("Referer");
    }

    /**
     * method is called when an HTTP POST request is sent to the /add-to-cart URL
     *
     * @param quantity of the product
     * @param productId of the product
     * @param model represents the data which is used to pass data to the view
     * @param principal represents the current logged-in user
     * @return String with redirected pages
     */
    @RequestMapping(value = "/update-cart", method = RequestMethod.POST, params = "action=update")
    public String updateCart(@RequestParam("quantity") int quantity,
                             @RequestParam("id") Long productId,
                             Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }else {
            String username = principal.getName();
            Customer customer = customerService.findByUsername(username);
            Product product = productService.getProductById(productId);
            ShoppingCart cart = cartService.updateItemCart(product, quantity, customer);

            model.addAttribute("shoppingCart", cart);
            return "redirect:/cart";
        }
    }

    /**
     * method is called when an HTTP POST request is sent to the /delete-cart URL
     *
     * @param productId of the product
     * @param model represents the data which is used to pass data to the view
     * @param principal represents the current logged-in user
     * @return String with redirected pages
     */
    @RequestMapping(value = "/delete-cart", method = RequestMethod.POST, params = "action=delete")
    public String deleteItemFormCart(@RequestParam("id") Long productId,
                             Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }else {
            String username = principal.getName();
            Customer customer = customerService.findByUsername(username);
            Product product = productService.getProductById(productId);
            ShoppingCart cart = cartService.deleteItemFromCart(product, customer);

            model.addAttribute("shoppingCart", cart);
            return "redirect:/cart";
        }
    }
}
