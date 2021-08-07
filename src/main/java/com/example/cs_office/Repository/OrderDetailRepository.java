package com.example.cs_office.Repository;


import com.example.cs_office.Model.Entity.OrderDetail;
import com.example.cs_office.Model.Entity.Orders;
import com.paypal.api.payments.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {
    //select Order Detail by id
    @Query("select s from OrderDetail s where s.id = ?1")
    Optional<OrderDetail> findOrderDetailById(Integer id);

    //select Order Detail by status
    @Query("select c from OrderDetail c where c.status = ?1")
    List<OrderDetail> findOrderDetailByStatus(boolean status);

    // select Order Detail by id Room
    @Query("select c from OrderDetail c where c.room.id = ?1 and c.acceptance = true and c.status = true")
    List<OrderDetail> getListOrderDetailByIdRoom(int idRoom);

    // select Order Detail by id order
    @Query("select c from OrderDetail c where c.orders2.id = ?1 and c.acceptance = true and c.status = true")
    List<OrderDetail> getListOrderDetailByIdOrder(int idOrder);

    //select order Detail by status == true and acceptance = false and idOrder
    @Query("select c from OrderDetail c where c.orders2.id = ?1 and c.status = true and c.acceptance = false ")
    List<OrderDetail> findOrderDetailByStatusAndAcceptanceAndIdOrder(int idOrder);

    //select order Detail by status == true and acceptance = false and idOrder
    @Query("select c from OrderDetail c where c.orders2.id = ?1 and c.status = true ")
    List<OrderDetail> findOrderDetailByCustomer(int idOrder);

    @Procedure(name = "OrderDetail.getCountOrderDetailEntity")
    int getCountOrderDetail();

    @Transactional
    @Modifying
    @Query("update OrderDetail c set c.acceptance = true where c.id = :idOrderDetail")
    int updateOrderDetailByIdOrderDetail(@Param("idOrderDetail") int idOrderDetail);

    @Transactional
    @Modifying
    @Query("update OrderDetail c set c.status = false where c.id = :idOrderDetail")
    int updateStatusByIdOrderDetail(@Param("idOrderDetail") int idOrderDetail);

    @Query("select  c from OrderDetail c where c.room.id = ?1 and c.status = true")
    List<OrderDetail> listOrderDetailByIdRoom(int idRoom);
}
