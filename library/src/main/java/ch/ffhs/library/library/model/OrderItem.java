package ch.ffhs.library.library.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * This class represents an order item
 */

import java.util.Date;

@Entity
@Table(name = "orderitems")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "quantity")
    private @NotNull int quantity;

    @Column(name = "price")
    private @NotNull double price;


    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "product_id")
    private Product product;

    public OrderItem() {
    }

    public OrderItem(Order order, @NotNull Product product, @NotNull int quantity, @NotNull double price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.order = order;
        this.createdDate = new Date();
    }
}