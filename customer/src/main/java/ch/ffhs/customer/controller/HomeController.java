package ch.ffhs.customer.controller;

import ch.ffhs.library.library.dto.ProductDto;
import ch.ffhs.library.library.model.Category;
import ch.ffhs.library.library.model.Customer;
import ch.ffhs.library.library.model.ShoppingCart;
import ch.ffhs.library.library.service.CategoryService;
import ch.ffhs.library.library.service.CustomerService;
import ch.ffhs.library.library.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * HomeController, which is responsible for processing requests related to the starting page
 */
@RestController
public class HomeController {
    // injects service for product management
    @Autowired
    private ProductService productService;

    // injects service for category management
    @Autowired
    private CategoryService categoryService;

    // injects service for customer management
    @Autowired
    private CustomerService customerService;

    /**
     * method is called when an HTTP GET request is sent to the /index URL
     *
     * @param principal represents the current logged-in user
     * @param session HttpSession
     * @return String with view's name "home.html"
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String home(Principal principal, HttpSession session){
        if(principal != null){
            session.setAttribute("username", principal.getName());
            Customer customer = customerService.findByUsername(principal.getName());
            ShoppingCart cart = customer.getShoppingCart();
            session.setAttribute("totalItems", cart.getTotalItems());
        } else {
            session.removeAttribute("username");
        }
        return "home";
    }

    /**
     * method is called when an HTTP GET request is sent to the /home URL
     *
     * @param model represents the data which is used to pass data to the view
     * @return String with view's name "index.html"
     */
    @GetMapping("/home")
    public String index(Model model){
        List<Category> categories = categoryService.findAll();
        List<ProductDto> productDtos = productService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("prodcuts", productDtos);
        return "index";
    }
}
