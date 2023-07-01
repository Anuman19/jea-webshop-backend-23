package ch.ffhs.library.library.service.impl;

import ch.ffhs.library.library.dto.CheckoutItemDto;
import ch.ffhs.library.library.dto.CustomerDto;
import ch.ffhs.library.library.model.Customer;
import ch.ffhs.library.library.model.Order;
import ch.ffhs.library.library.repository.OrderItemRepository;
import ch.ffhs.library.library.repository.OrderRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@Transactional
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemsRepository;

    @Autowired
    CustomerServiceImpl customerService;

    // create total price
    SessionCreateParams.LineItem.PriceData createPriceData(CheckoutItemDto checkoutItemDto) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("chf")
                .setUnitAmount((long) (checkoutItemDto.getPrice() * 100))
                .setProductData(
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(checkoutItemDto.getProductName())
                                .build())
                .build();
    }

    // build each product in the stripe checkout page
    SessionCreateParams.LineItem createSessionLineItem(CheckoutItemDto checkoutItemDto) {
        return SessionCreateParams.LineItem.builder()
                // set price for each product
                .setPriceData(createPriceData(checkoutItemDto))
                // set quantity for each product
                .setQuantity(Long.parseLong(String.valueOf(checkoutItemDto.getQuantity())))
                .build();
    }

    // create session from list of checkout items
    public Session createSession(List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {

        // set the private key
        Stripe.apiKey = "sk_test_51MvcTPGlapSqHKmNyYGfy33pJzOIJR8mgOppHDWhVJHzrF3xWol0V2h3VZeGTHzcOnciKRVyT6AIfxzthLrgXZMM00lmNLjTl0";

        List<SessionCreateParams.LineItem> sessionItemsList = new ArrayList<>();

        // for each product compute SessionCreateParams.LineItem
        for (CheckoutItemDto checkoutItemDto : checkoutItemDtoList) {
            sessionItemsList.add(createSessionLineItem(checkoutItemDto));
        }


        // build the session param
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .addAllLineItem(sessionItemsList)
                .setSuccessUrl("http://localhost:9000/#/payment/success/{CHECKOUT_SESSION_ID}")
                .setCancelUrl("http://localhost:9000/#/payment/failed/{CHECKOUT_SESSION_ID}")
                .build();

        return Session.create(params);
    }

    public Order placeOrder(String sessionId, CheckoutItemDto checkoutItemDto) {

        // create the order and save it
        Order newOrder = new Order();
        newOrder.setCreatedDate(new Date());
        newOrder.setSessionId(sessionId);
        newOrder.setUser(customerService.findCustomerById((long) checkoutItemDto.getUserId()));
        newOrder.setTotalPrice(checkoutItemDto.getPrice());
        return orderRepository.save(newOrder);

        /**for (CartItemDto cartItemDto : cartItemDtoList) {
         // create orderItem and save each one
         OrderItem orderItem = new OrderItem();
         orderItem.setCreatedDate(new Date());
         orderItem.setPrice(cartItemDto.getProduct().getPrice());
         orderItem.setProduct(cartItemDto.getProduct());
         orderItem.setQuantity(cartItemDto.getQuantity());
         orderItem.setOrder(newOrder);
         // add to order item list
         orderItemsRepository.save(orderItem);
         }
         //
         cartService.deleteUserCartItems(user);**/
    }

    public List<Order> listOrders(Long id) {
        return orderRepository.findAllByUserOrderByCreatedDateDesc(customerService.findByUsername(customerService.findCustomerById(id).getUsername()));
    }


    public Order getOrder(Integer orderId) throws Exception {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            return order.get();
        }
        throw new Exception("Order not found");
    }


}

