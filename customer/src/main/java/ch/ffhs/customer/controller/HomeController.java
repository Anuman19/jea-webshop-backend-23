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

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String home(Model model, Principal principal, HttpSession session){
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

    @GetMapping("/home")
    public String index(Model model){
        List<Category> categories = categoryService.findAll();
        List<ProductDto> productDtos = productService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("prodcuts", productDtos);
        return "index";
    }
}
