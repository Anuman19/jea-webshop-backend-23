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
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private Long id;
    @Size(min = 1, max = 30, message = "First name should have 1-30 characters")
    private String firstName;
    @Size(min = 1, max = 50, message = "Last name should have 1-50 characters")
    private String lastName;
    @Size(min = 1, max = 50, message = "Last name should have 1-50 characters")
    private String username;
    private String email;
    @Size(min = 8, max = 30, message = "Password should have 8-30 characters")
    private String password;

}
