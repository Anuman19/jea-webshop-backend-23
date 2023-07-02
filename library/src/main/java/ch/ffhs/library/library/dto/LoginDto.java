package ch.ffhs.library.library.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * This class is a data transfer object that is used in Spring-based applications
 * to transfer data between the frontend and the backend
 */
public class LoginDto {
    private String email;
    private String password;
}