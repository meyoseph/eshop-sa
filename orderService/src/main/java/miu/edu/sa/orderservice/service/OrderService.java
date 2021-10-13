package miu.edu.sa.orderservice.service;

import javassist.NotFoundException;
import miu.edu.sa.orderservice.dto.OrderRequest;
import miu.edu.sa.orderservice.dto.OrderResponse;
import miu.edu.sa.orderservice.dto.Payment;
import miu.edu.sa.orderservice.dto.Product;
import miu.edu.sa.orderservice.model.Order;
import miu.edu.sa.orderservice.proxy.PaymentProxy;
import miu.edu.sa.orderservice.proxy.productProxy;
import miu.edu.sa.orderservice.respository.OrderRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    //private RestTemplate restTemplate;
    private PaymentProxy paymentProxy;
    private productProxy productProxy;

   Logger logger = LoggerFactory.getLogger(OrderService.class);


    public OrderService(OrderRepository orderRepository, PaymentProxy paymentProxy, productProxy productProxy) {
        this.orderRepository = orderRepository;
        this.paymentProxy = paymentProxy;
        this.productProxy = productProxy;
    }


    public OrderResponse placeOrder(OrderRequest orderRequest) throws NotFoundException {

        Order order = orderRequest.getOrder();
        Payment payment = orderRequest.getPayment();
        payment.setPaymentMethod(order.getPaymentMethod());

        Optional<Product> optionalProduct = Optional.ofNullable(productProxy.getProductById(order.getProductId()).getBody());


        Product product;
        Integer quantity;

        if(optionalProduct.isPresent()) {
            product = optionalProduct.get();

            quantity = product.getQuantity()- order.getQuantity();
            product.setQuantity(order.getQuantity());
            Product p = productProxy.updateProduct(order.getProductId(),quantity).getBody();

            logger.info(""+ product.getQuantity() + " " + order.getQuantity());
        }
        else {
            throw new NotFoundException("there is no product with the given ID : "+ order.getProductId());
        }

        Payment paymentResponse = paymentProxy.makePayment(payment);
        orderRepository.save(order);

        logger.info("your order is placed successfully");

        return new OrderResponse(product, paymentResponse);

    }
}
