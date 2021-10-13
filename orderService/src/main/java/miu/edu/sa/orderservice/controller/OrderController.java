package miu.edu.sa.orderservice.controller;

import javassist.NotFoundException;
import miu.edu.sa.orderservice.dto.OrderRequest;
import miu.edu.sa.orderservice.dto.OrderResponse;
import miu.edu.sa.orderservice.model.Order;
import miu.edu.sa.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/placeOrder")
    public OrderResponse placeOrder(@RequestBody OrderRequest orderRequest) throws NotFoundException {
        return orderService.placeOrder(orderRequest);
    }

}
