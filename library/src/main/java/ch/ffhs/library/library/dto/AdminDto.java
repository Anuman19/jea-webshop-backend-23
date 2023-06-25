package ch.ffhs.library.library.dto;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is a data transfer object that is used in Spring-based applications
 * to transfer data between the frontend and the backend
 */
@Data @NoArgsConstructor @AllArgsConstructor
public class AdminDto {
    @Size(min = 3, max = 12, message = "Invalid first name! (3-12 characters")
    private String firstName;
    @Size(min = 3, max = 12, message = "Invalid last name! (3-12 characters")
    private String lastName;
    private String username;
    @Size(min = 12, max = 20, message = "Invalid password! (12-20 characters")
    private String password;
    private String repeatPassword;
}
