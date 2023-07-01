package ch.ffhs.customer.controller;


import ch.ffhs.library.library.dto.CheckoutItemDto;
import ch.ffhs.library.library.dto.CustomerDto;
import ch.ffhs.library.library.dto.StripeResponse;
import ch.ffhs.library.library.model.Customer;
import ch.ffhs.library.library.model.Order;
import ch.ffhs.library.library.model.OrderItem;
import ch.ffhs.library.library.repository.OrderItemRepository;
import ch.ffhs.library.library.repository.OrderRepository;
import ch.ffhs.library.library.service.ProductService;
import ch.ffhs.library.library.service.impl.CustomerServiceImpl;
import ch.ffhs.library.library.service.impl.OrderItemsService;
import ch.ffhs.library.library.service.impl.OrderService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemsService orderItemsService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private ProductService productService;

    @PostMapping("/create-checkout-session")
    public ResponseEntity<?> checkoutList(@RequestBody List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {

        try {
            Session session = orderService.createSession(checkoutItemDtoList);
            StripeResponse stripeResponse = new StripeResponse(session.getId());
            return new ResponseEntity<>(stripeResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }

    }

    // place order after checkout
    @PostMapping("/add")
    public ResponseEntity<String> placeOrder(@RequestParam("sessionId") String sessionId, @RequestBody() List<CheckoutItemDto> checkoutItemDtoList) {
        // place the order


        Order order = orderService.placeOrder(sessionId, checkoutItemDtoList.get(0));

        for (CheckoutItemDto checkoutItemDto : checkoutItemDtoList) {

            orderItemsService.addOrderedProducts(new OrderItem(order, productService.getProductById(checkoutItemDto.getProductId()), checkoutItemDto.getQuantity(), checkoutItemDto.getPrice()));
        }

        return new ResponseEntity<>("Order has been placed", HttpStatus.CREATED);
    }

    // get all orders
    @GetMapping("/{id}")
    public ResponseEntity<List<Order>> getByUserId(@PathVariable("id") Long id) {

        // get orders
        List<Order> orderDtoList = orderService.listOrders(id);

        return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllOrders() {
        return new ResponseEntity<>(orderItemRepository.findAll(), HttpStatus.OK);
    }


    // get orderitems for an order
    /**
     @GetMapping("/{id}") public ResponseEntity<Object> getOrderById(@PathVariable("id") Integer id, @RequestParam("user") Customer user) {

     try {
     Order order = orderService.getOrder(id);
     return new ResponseEntity<>(order, HttpStatus.OK);
     } catch (Exception e) {
     return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
     }

     }**/

}
