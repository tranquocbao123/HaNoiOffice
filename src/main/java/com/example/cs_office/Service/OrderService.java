package com.example.cs_office.Service;

import com.example.cs_office.Model.Evaluate;
import com.example.cs_office.Model.Order;
import com.example.cs_office.Model.Room;
import com.example.cs_office.Repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrder() {
        return orderRepository.findAll();
    }

    public List<Order> getOrderByStatus(boolean status) {

        return orderRepository.findOrderByStatus(status);
    }

    public Optional<Order> getOrderById(int orderId) {
        Optional<Order> order = orderRepository.findOrderById(orderId);
        return order;
    }

    public void addNewOrder(Order order) {
        Optional<Order> orderOptional =
                orderRepository.findOrderById(order.getId());
        if (orderOptional.isPresent()) {
            throw new IllegalStateException("Id taken");
        }
        orderRepository.save(order);
    }

    public void deleteOrder(int orderId) {
        boolean exists = orderRepository.existsById(orderId);
        if (!exists) {
            throw new IllegalStateException("order with id " + orderId + " does not exists");
        }
        orderRepository.deleteById(orderId);
    }

    public Order updateOrder(Order order, int orderId){
        Order order1 = this.orderRepository.getOne(orderId);
        BeanUtils.copyProperties(order,order1);
        return orderRepository.saveAndFlush(order1);
    }
}
