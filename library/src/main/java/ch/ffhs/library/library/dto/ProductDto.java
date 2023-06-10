package ch.ffhs.library.library.dto;

import ch.ffhs.library.library.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int currentQuantity;
    private Category category;
    private String image;
    private boolean activated;
}
