package ch.ffhs.customer.controller;

import ch.ffhs.library.library.model.Customer;
import ch.ffhs.library.library.model.Order;
import ch.ffhs.library.library.model.ShoppingCart;
import ch.ffhs.library.library.service.CustomerService;
import ch.ffhs.library.library.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * OrderController, which is responsible for processing requests related to orders
 */
@RestController
public class OrderController {
    // this annotation injects the CustomerService which allows the controller
    // to access the service to execute business logic related to customers
    @Autowired
    private CustomerService customerService;

    // injects service for order management
    @Autowired
    private OrderService orderService;

    /**
     * method is called when an HTTP GET request is sent to the /check-out URL
     *
     * @param model represents the data which is used to pass data to the view
     * @param principal represents the current logged-in user
     * @return String with view's name or redirected URL
     */
    @GetMapping("/check-out")
    public String checkout(Model model, Principal principal){
        /**if(principal == null){
            return "redirect:/login";
        }**/
        // username will be extracted from principle
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);
        // if address, city or country are empty, user will be directed to account page
        if(customer.getAddress().trim().isEmpty()|| customer.getCity().toString().isEmpty()|| customer.getCountry().trim().isEmpty()){
            model.addAttribute("customer", customer);
            model.addAttribute("error", "You must fill out the information after the checkout");
            return "account";
        }else {
            // customer's shopping cart will be added to the model object
            model.addAttribute("customer", customer);
            ShoppingCart cart = customer.getShoppingCart();
            model.addAttribute("cart", cart);
        }
        return "check-out";
    }

    /**
     * method is called when an HTTP GET request is sent to the /order URL
     *
     * @param principal represents the current logged-in user
     * @param model represents the data which is used to pass data to the view
     * @return String with view's name "order.html" or redirected URL
     */
    @GetMapping("/order")
    public String order(Principal principal, Model model){
        /**if(principal == null){
            return "redirect:/login";
        }**/
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);
        List<Order> orderList = customer.getOrders();
        model.addAttribute("orders", orderList);
        return "order";
    }

    /**
     * method is called when an HTTP GET request is sent to the /save-order URL
     * after this process the user will be redirected to another page
     *
     * @param principal represents the current logged-in user
     * @return String with redirected URL
     */
    @GetMapping("/save-order")
    public String saveOrder(Principal principal){
        /**if(principal == null){
            return "redirect:/login";
        }**/
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);
        ShoppingCart cart = customer.getShoppingCart();
        orderService.saveOrder(cart);
        return "redirect:/order";
    }
}
