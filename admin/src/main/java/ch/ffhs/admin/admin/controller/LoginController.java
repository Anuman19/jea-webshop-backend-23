package ch.ffhs.admin.admin.controller;

import ch.ffhs.library.library.dto.AdminDto;
import ch.ffhs.library.library.dto.LoginDto;
import ch.ffhs.library.library.model.Admin;
import ch.ffhs.library.library.repository.AdminRepository;
import ch.ffhs.library.library.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


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
     * method is called when an HTTP POST request is sent to the /login URL
     *
     * @return ResponseEntity with user id and 200
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        Admin admin;
        if (adminService.findByEmail(loginDto.getEmail()) == null) {
            return new ResponseEntity<>("user not found", HttpStatus.CONFLICT);
        } else {
            admin = adminService.findByEmail(loginDto.getEmail());

            if (admin.getPassword().equals(loginDto.getPassword())) {
                return new ResponseEntity<>(admin.getId(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("password not matching", HttpStatus.FORBIDDEN);
            }
        }
    }

    /**
     * method is called when an HTTP POST request is sent to the /register URL
     *
     * @return ResponseEntity newly registered user and 201
     */
    @PostMapping("/register")
    public ResponseEntity<Admin> register(@RequestBody AdminDto adminDto) {

        adminService.save(adminDto);
        return new ResponseEntity<>(adminService.save(adminDto), HttpStatus.CREATED);
    }

    /**
     * method is called when an HTTP GET request is sent to the /get-user/{id} URL
     *
     * @param id of user
     * @return ResponseEntity user by id and 200
     */
    @GetMapping("/get-user/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {

        if (adminRepository.findById(id).isPresent()) {
            return new ResponseEntity<>(adminRepository.findById(id).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("not found", HttpStatus.CONFLICT);
        }

    }

    /**
     * method is called when an HTTP GET request is sent to the /users URL
     *
     * @return ResponseEntity all users and 200
     */
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(adminRepository.findAll(), HttpStatus.OK);
    }

    /**
     * method is called when an HTTP PUT request is sent to the /update-user/{id} URL
     *
     * @param id       of user
     * @param adminDto with changes
     * @return ResponseEntity updated user and 200
     */
    @PutMapping("/update-user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody AdminDto adminDto) {

        if (adminRepository.findById(id).isPresent()) {
            return new ResponseEntity<>(adminService.update(adminDto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("not found", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {

        try {
            adminRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

}
