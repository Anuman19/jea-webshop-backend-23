package ch.ffhs.admin.admin.controller;

import ch.ffhs.library.library.dto.AdminDto;
import ch.ffhs.library.library.dto.LoginDto;
import ch.ffhs.library.library.model.Admin;
import ch.ffhs.library.library.repository.AdminRepository;
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
import java.util.Optional;


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

    @Autowired
    AdminRepository adminRepository;

    /**
     * method is called when an HTTP GET request is sent to the /login URL
     *
     * @param model represents the data which is used to pass data to the view
     * @return String with view's name "login.html"
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        Admin admin;
        try {
            admin = adminService.findByEmail(loginDto.getEmail());
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.CONFLICT);
        }
        if (admin.getPassword().equals(loginDto.getPassword())) {
            return new ResponseEntity<>(admin.getId(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("password not matching", HttpStatus.FORBIDDEN);
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

    @GetMapping("/get-user/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {

        if (adminRepository.findById(id).isPresent()) {
            return new ResponseEntity<>(adminRepository.findById(id).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("not found", HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(adminRepository.findAll(), HttpStatus.OK);
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody AdminDto adminDto) {

        if (adminRepository.findById(id).isPresent()) {
            return new ResponseEntity<>(adminService.update(adminDto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("not found", HttpStatus.BAD_REQUEST);
        }
    }

}
