package ch.ffhs.customer.controller;

import ch.ffhs.library.library.model.Customer;
import ch.ffhs.library.library.service.CityService;
import ch.ffhs.library.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

/**
 * AccountController, which is responsible for processing requests related to accounts
 */
@Controller
public class AccountController {
    // injects service for customer's management
    @Autowired
    private CustomerService customerService;

    // injects service for city management
    @Autowired
    private CityService cityService;

    /**
     * method is called when an HTTP GET request is sent to the /account URL
     *
     * @param model represents the data which is used to pass data to the view
     * @param principal represents the current logged-in user
     * @return String with view's name "account.html"
     */
    @GetMapping("/account")
    public String accountHome(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);
        model.addAttribute("customer", customer);
        return "account";
    }

    /**
     * method is called when an HTTP GET and PUT request are sent to the /update-information URL
     *
     * @param customer object to save information
     * @param attributes flash attributes show error or success messages
     * @param principal represents the current logged-in user
     * @return String with redirected pages
     */
    @RequestMapping(value = "/update-information", method = {RequestMethod.GET, RequestMethod.PUT})
    public String updateCustomer(@ModelAttribute("customer") Customer customer, RedirectAttributes attributes,
                                 Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        Customer customerSaved = customerService.saveCustomerInfo(customer);
        attributes.addFlashAttribute("customer", customerSaved);
        return "redirect:/account";
    }
}
