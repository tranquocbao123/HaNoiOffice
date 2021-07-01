package com.example.cs_office.Service;


import com.example.cs_office.Model.OrderDetail;
import com.example.cs_office.Repository.OrderDetailRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<OrderDetail> getOrderDetail() {
        return orderDetailRepository.findAll();
    }

    public List<OrderDetail> getOrderDetailByStatus(boolean status) {

        return orderDetailRepository.findOrderDetailByStatus(status);
    }

    public Optional<OrderDetail> getOrderDetailById(int orderdetailId) {
        Optional<OrderDetail> orderdetail = orderDetailRepository.findById(orderdetailId);
        return orderdetail;
    }

    public void addNewOrderDetail(OrderDetail orderDetail) {
        Optional<OrderDetail> orderDetailOptional =
                orderDetailRepository.findOrderDetailById(orderDetail.getId());
        if (orderDetailOptional.isPresent()) {
            throw new IllegalStateException("Id taken");
        }
        orderDetailRepository.save(orderDetail);
    }

    public void deleteOrderDetail(int orderDetailId) {
        boolean exists = orderDetailRepository.existsById(orderDetailId);
        if (!exists) {
            throw new IllegalStateException("order detail with id " + orderDetailId + " does not exists");
        }
        orderDetailRepository.deleteById(orderDetailId);
    }

    @Transactional
    public OrderDetail updateOrderDetailStatus(OrderDetail orderDetail) {
        orderDetail.setStatus(false);
        return orderDetailRepository.save(orderDetail);
    }

    @Transactional
    public OrderDetail updateOrderDetailBlack(OrderDetail orderDetail) {
        orderDetail.setStatus(true);
        return orderDetailRepository.save(orderDetail);
    }

    @Transactional
    public OrderDetail updateOrderDetail(OrderDetail orderDetail, int orderDetailId){
        OrderDetail orderDetail1 = this.orderDetailRepository.getOne(orderDetailId);
        BeanUtils.copyProperties(orderDetail,orderDetail1);
        return orderDetailRepository.saveAndFlush(orderDetail1);
    }

}
