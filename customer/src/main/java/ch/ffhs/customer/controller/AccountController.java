package ch.ffhs.customer.controller;

import ch.ffhs.library.library.model.City;
import ch.ffhs.library.library.model.Customer;
import ch.ffhs.library.library.service.CityService;
import ch.ffhs.library.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class AccountController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CityService cityService;

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

    @RequestMapping(value = "/update-information", method = {RequestMethod.GET, RequestMethod.PUT})
    public String updateCustomer(@ModelAttribute("customer") Customer customer,
                                 Model model, RedirectAttributes attributes,
                                 Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        Customer customerSaved = customerService.saveCustomerInfo(customer);
        attributes.addFlashAttribute("customer", customerSaved);

        return "redirect:/account";
    }
}
