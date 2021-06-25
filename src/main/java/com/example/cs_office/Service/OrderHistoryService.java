package com.example.cs_office.Service;

import com.example.cs_office.Model.OrderHistory;
import com.example.cs_office.Model.Orders;
import com.example.cs_office.Repository.OrderHistoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OrderHistoryService {
    @Autowired
    private final OrderHistoryRepository orderHistoryRepository;

    public OrderHistoryService(OrderHistoryRepository orderHistoryRepository) {
        this.orderHistoryRepository = orderHistoryRepository;
    }


    public List<OrderHistory> getOrderHistory() {
        return orderHistoryRepository.findAll();
    }

    public List<OrderHistory> getOrderHistoryByStatus(boolean status) {

        return orderHistoryRepository.findOrderHistoryByStatus(status);
    }

    public Optional<OrderHistory> getOrderHistoryById(int orderhitoryId) {
        Optional<OrderHistory> orderHistory = orderHistoryRepository.findById(orderhitoryId);
        return orderHistory;
    }

    public void addNewOrderHistory(OrderHistory orderHistory) {
        Optional<OrderHistory> orderHistoryOptional =
                orderHistoryRepository.findOrderHistoryById(orderHistory.getId());
        if (orderHistoryOptional.isPresent()) {
            throw new IllegalStateException("Id taken");
        }
        orderHistoryRepository.save(orderHistory);
    }

    public void deleteOrderHistory(int orderHistoryId) {
        boolean exists = orderHistoryRepository.existsById(orderHistoryId);
        if (!exists) {
            throw new IllegalStateException("order history with id " + orderHistoryId + " does not exists");
        }
        orderHistoryRepository.deleteById(orderHistoryId);
    }

    @Transactional
    public OrderHistory updateOrderHistoryStatus(OrderHistory orderHistory) {
        orderHistory.setStatus(false);
        return orderHistoryRepository.save(orderHistory);
    }

    @Transactional
    public OrderHistory updateOrderHistoryBlack(OrderHistory orderHistory) {
        orderHistory.setStatus(true);
        return orderHistoryRepository.save(orderHistory);
    }

    @Transactional
    public OrderHistory updateOrderHistory(OrderHistory orderHistory, int orderHistoryId){
        OrderHistory orderHistory1 = this.orderHistoryRepository.getOne(orderHistoryId);
        BeanUtils.copyProperties(orderHistory,orderHistory1);
        return orderHistoryRepository.saveAndFlush(orderHistory1);
    }
}
