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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.Authenticator;


/**
 * This controller is created to map incoming request URL
 */
@Controller
public class LoginController {
    //is used to handle GET requests

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AdminServiceImpl adminService;
    @GetMapping("/login")//see AdminConfiguration ... loginPage
    public String viewLoginPage(Model model){
        //will be show in browser tab (<title> tag)
        model.addAttribute("title", "Login");
        //custom login before showing login page
        return "login";
    }

    //used for the "Create Account" bottom on the login page
    @GetMapping("/register")
    public String register(Model model){
        //will be show in browser tab (<title> tag)
        model.addAttribute("title", "Register");
        model.addAttribute("adminDto", new AdminDto());
        return "register";
    }

    @RequestMapping("/index")
    public String home(Model model){
        //will be show in browser tab (<title> tag)
        model.addAttribute("title", "Home Page");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // if there hasn't been a correct authentication process or the user is part anonymous
        // you will return to the login page
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "redirect:/login";
        }
        return "index";
    }

    //if the user can't remember his password, he will be redirected to another page where he can change his password
    @GetMapping("/forgot-password")
    public String forgotPassword(Model model){
        //will be show in browser tab (<title> tag)
        model.addAttribute("title", "Forgot Password");
        return "forgot-password";
    }

    @PostMapping("/register-new")
    private String addNewAdmin(@Valid @ModelAttribute("adminDto")AdminDto adminDto,
                               BindingResult result,
                               Model model){
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
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("errors", "Something went wrong");
        }
        return "register";

    }
}
