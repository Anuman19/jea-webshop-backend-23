package ch.ffhs.admin.admin.controller;

import ch.ffhs.library.library.dto.AdminDto;
import ch.ffhs.library.library.dto.LoginDto;
import ch.ffhs.library.library.model.Admin;
import ch.ffhs.library.library.service.impl.AdminServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        Admin admin = adminService.findByEmail(loginDto.getEmail());
        System.out.println(admin.getPassword());
        if (admin == null) {
            return new ResponseEntity<>("no such user", HttpStatus.FORBIDDEN);
        } else {
            if (admin.getPassword().equals(loginDto.getPassword())) {
                return new ResponseEntity<>(admin.toString(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("password not matching", HttpStatus.FORBIDDEN);
            }
        }
    }

    /**
     * method is called when an HTTP GET request is sent to the /register URL
     *
     * @param model represents the data which is used to pass data to the view
     * @return String with view's name "register.html"
     */
    @PostMapping("/register")
    public ResponseEntity<Admin> register(AdminDto adminDto) {

        return new ResponseEntity<>(adminService.save(adminDto), HttpStatus.CREATED);
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
    @PostMapping("/register-old")
    public ResponseEntity<String> addNewAdmin(@Valid @ModelAttribute("adminDto") AdminDto adminDto,
                                              BindingResult result) {
        System.out.println(result);
        try {
            if (result.hasErrors()) {

                return new ResponseEntity<>(result.getAllErrors().toString(), HttpStatus.BAD_REQUEST);
            }
            String username = adminDto.getUsername();
            Admin admin = adminService.findByUsername(username);
            if (admin != null) {
                return new ResponseEntity<>("Already exists!", HttpStatus.CONFLICT);
            }
            if (adminDto.getPassword() != null) {
                adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));
                return new ResponseEntity<>(adminService.save(adminDto).toString(), HttpStatus.CREATED);
            } else {

                return new ResponseEntity<>("Password is empty!", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Oops", HttpStatus.INTERNAL_SERVER_ERROR);
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
