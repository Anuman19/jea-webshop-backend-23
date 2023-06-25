package ch.ffhs.admin.admin.controller;

import ch.ffhs.library.library.dto.AdminDto;
import ch.ffhs.library.library.model.Admin;
import ch.ffhs.library.library.service.impl.AdminServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.Authenticator;


/**
 * LoginController is created to map incoming URL request related to the login
 * and is responsible for displaying the appropriate views and processing user interaction to perform
 * the desired actions (displaying pages, saving data and redirecting)
 */
@RestController
public class LoginController {

    // is used to encrypt passwords
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // injects service for administrators' management
    @Autowired
    private AdminServiceImpl adminService;

    /**
     * method is called when an HTTP GET request is sent to the /login URL
     *
     * @param model represents the data which is used to pass data to the view
     * @return String with view's name "login.html"
     */
    @GetMapping("/login")
    public String viewLoginPage(Model model) {
        //will be show in browser tab (<title> tag)
        return "login";
    }

    /**
     * method is called when an HTTP GET request is sent to the /register URL
     *
     * @param model represents the data which is used to pass data to the view
     * @return String with view's name "register.html"
     */
    @GetMapping("/register")
    public String register(Model model) {
        //will be show in browser tab (<title> tag)
        model.addAttribute("title", "Register");
        // adds an empty AdminDto object to the model, which is used for entering registration information
        model.addAttribute("adminDto", new AdminDto());
        return "register";
    }

    /**
     * method is called when an HTTP GET request is sent to the /index URL
     *
     * @param model represents the data which is used to pass data to the view
     * @return String with view's name "index.html"
     */
    @RequestMapping("/index")
    public String home(Model model) {
        //will be show in browser tab (<title> tag)
        model.addAttribute("title", "Home Page");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // if there hasn't been a correct authentication process or the user is part anonymous
        // the user will be returned to the login page
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "redirect:/login";
        }
        return "index";
    }

    /**
     * method is called when an HTTP GET request is sent to the /forgot-password URL
     *
     * @param model represents the data which is used to pass data to the view
     * @return String with view's name "forgot-password.html"
     */
    //if the user can't remember his password, he will be redirected to another page where he can reset his password
    @GetMapping("/forgot-password")
    public String forgotPassword(Model model) {
        //will be show in browser tab (<title> tag)
        model.addAttribute("title", "Forgot Password");
        return "forgot-password";
    }

    /**
     * method is called when an HTTP POST request is sent to the /register-new URL
     * and processes the registration request, checks the input validation and stores
     * the new administrator in the database
     *
     * @param adminDto data transfer object with the admin data
     * @param result   object to save validation errors
     * @param model    represents the data which is used to pass data to the view
     * @return String with attribute or view's name "register.html"
     */
    @PostMapping("/register-new")
    private String addNewAdmin(@Valid @ModelAttribute("adminDto") AdminDto adminDto,
                               BindingResult result,
                               Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("adminDto", adminDto);
                result.toString();
                return "register";
            }
            String username = adminDto.getUsername();
            Admin admin = adminService.findByUsername(username);
            if (admin != null) {
                model.addAttribute("adminDto", adminDto);
                System.out.println("admin not null");
                model.addAttribute("mailError", "Your email has been registered");
                return "register";
            }
            if (adminDto.getPassword().equals(adminDto.getRepeatPassword())) {
                adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));
                adminService.save(adminDto);
                System.out.println("success");
                model.addAttribute("success", "Register successfully");
                model.addAttribute("adminDto", adminDto);
            } else {
                // if the repeated password isn't identical -> redirect to register page
                model.addAttribute("passwordError", "Your password maybe wrong, please check again");
                model.addAttribute("adminDto", adminDto);
                System.out.println("password not same");
                return "/register";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errors", "Something went wrong");
        }
        return "register";
    }


    @GetMapping("/signup")
    public String showSignUpForm(AdminDto user) {
        return "register";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid AdminDto user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        adminService.save(user);
        return "redirect:/index";
    }
}
