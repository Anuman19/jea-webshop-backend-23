package ch.ffhs.admin.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This controller is created to map incoming request URL
 */
@Controller
public class LoginController {
    //is used to handle GET requests
    @GetMapping("/login")//see AdminConfiguration ... loginPage
    public String viewLoginPage(){
        //custom login before showing login page
        return "login";
    }

    //used for the "Create Account" bottom on the login page
    @GetMapping("/register")
    public String register(Model model){
        return "register";
    }

    //if the user can't remember his password, he will be redirected to another page where he can change his password
    @GetMapping("/forgot-password")
    public String forgotPassword(Model model){
        return "forgot-password";
    }

}
