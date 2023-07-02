package ch.ffhs.library.library.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is a data transfer object that is used in Spring-based applications
 * to transfer data between the frontend and the backend
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {

    private Long id;
    @Size(min = 1, max = 30, message = "Invalid first name! (1-30 characters")
    private String firstName;
    @Size(min = 1, max = 50, message = "Invalid last name! (1-50 characters")
    private String lastName;
    @Size(min = 1, max = 50, message = "Invalid username! (1-50 characters")
    private String username;
    private String email;
    @Size(min = 8, max = 30, message = "Invalid password! (8-30 characters")
    private String password;
}
