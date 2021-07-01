package com.example.cs_office.Service;

import com.example.cs_office.Model.Entity.Orders;
import com.example.cs_office.Repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Orders> getOrder() {
        return orderRepository.findAll();
    }

    public List<Orders> getOrderByStatus(boolean status) {

        return orderRepository.findOrderByStatus(status);
    }

    public Optional<Orders> getOrderById(int orderId) {
        Optional<Orders> order = orderRepository.findOrderById(orderId);
        return order;
    }

    public void addNewOrder(Orders orders) {
        Optional<Orders> orderOptional =
                orderRepository.findOrderById(orders.getId());
        if (orderOptional.isPresent()) {
            throw new IllegalStateException("Id taken");
        }
        orderRepository.save(orders);
    }

    public void deleteOrder(int orderId) {
        boolean exists = orderRepository.existsById(orderId);
        if (!exists) {
            throw new IllegalStateException("order with id " + orderId + " does not exists");
        }
        orderRepository.deleteById(orderId);
        System.out.println(orderId);
    }

    @Transactional
    public Orders updateOrdersStatus(Orders orders) {
        orders.setStatus(false);
        return orderRepository.save(orders);
    }

    @Transactional
    public Orders updateOrdersBlack(Orders orders) {
        orders.setStatus(true);
        return orderRepository.save(orders);
    }

    @Transactional
    public Orders updateOrder(Orders orders, int orderId){
        Orders orders1 = this.orderRepository.getOne(orderId);
        BeanUtils.copyProperties(orders, orders1);
        return orderRepository.saveAndFlush(orders1);
    }
}
