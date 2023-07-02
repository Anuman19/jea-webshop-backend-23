package ch.ffhs.library.library.dto;

import ch.ffhs.library.library.model.Order;
import jakarta.validation.constraints.NotNull;

/**
 * This class is a data transfer object that is used in Spring-based applications
 * to transfer data between the frontend and the backend
 */
public class OrderDto {

    private Integer id;
    private @NotNull Integer userId;

    public OrderDto() {
    }
    public OrderDto(Order order) {
        this.setId(order.getId());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
