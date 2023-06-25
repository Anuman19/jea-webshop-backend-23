package ch.ffhs.library.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is a data transfer object that is used in Spring-based applications
 * to transfer data between the frontend and the backend
 */
@NoArgsConstructor
@AllArgsConstructor
@Data

public class CategoryDto {
    private Long categoryId;
    private String categoryName;
    private Long numberOfProducts;
}
