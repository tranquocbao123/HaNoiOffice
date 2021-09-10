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

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    //select Order Detail by id
    @Query("select s from OrderDetail s where s.id = ?1")
    Optional<OrderDetail> findOrderDetailById(Integer id);

    //select Order Detail by id room
    @Query("select s from OrderDetail s where s.room.id = ?1 ")
    List<OrderDetail> findOrderDetailByIdRoom(Integer id);

    //select Order Detail by id room
    @Query("select s from OrderDetail s where s.room.id = ?1 and acceptance = true")
    List<OrderDetail> findOrderDetailByIdRoom1(Integer id);

    //select Order Detail by status
    @Query("select c from OrderDetail c where c.status = ?1")
    List<OrderDetail> findOrderDetailByStatus(boolean status);

    // select Order Detail by id Room
    @Query("select c from OrderDetail c where c.room.id = ?1 and c.acceptance = true and c.status = true")
    List<OrderDetail> getListOrderDetailByIdRoom(int idRoom);

    // select Order Detail by id order
    @Query("select c from OrderDetail c where c.orders2.id = ?1 and c.acceptance = true and c.status = true")
    List<OrderDetail> getListOrderDetailByIdOrder(int idOrder);

    // select Order Detail by id order
    @Query("select c from OrderDetail c where c.done = false and c.orders2.id = ?1 order by createDate desc")
    List<OrderDetail> getListOrderDetailByIdOrder1(int idOrder);

    // lich su order
    @Query("select c from OrderDetail c where c.done = true and c.orders2.id = ?1 order by createDate desc")
    List<OrderDetail> getListOrderDetailByIdOrder2(int idOrder);

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

    @Transactional
    @Modifying
    @Query("update OrderDetail c set c.done = true where c.id = :idOrderDetail")
    int updateDone(@Param("idOrderDetail") int idOrderDetail);

    @Query("select  c from OrderDetail c where c.room.id = ?1 and c.status = true")
    List<OrderDetail> listOrderDetailByIdRoom(int idRoom);

    @Query(value = "{CALL get_total_by_idOrderDetail(:id_Order)}", nativeQuery = true)
    String getTotalByIdOrderDetail(@Param("id_Order") int idOrderDetail);

    @Query(value = "{CALL total_order_by_date(:startDate, :endDate)}", nativeQuery = true)
    String getTotalByDate(@Param("startDate") Date startDate,
                          @Param("endDate") Date endDate);

    @Query(value = "{CALL price_service_by_date(:idOrderDetail)}", nativeQuery = true)
    String getPriceServiceByDate(@Param("idOrderDetail") int idOrderDetail);

    @Query(value = "{CALL price_schedule_by_date(:idOrderDetail)}", nativeQuery = true)
    String getPriceScheduleByDate(@Param("idOrderDetail") int idOrderDetail);

    @Query(value = "{CALL total_order}", nativeQuery = true)
    String getTotal();

    @Query("select c from OrderDetail c where c.done = false order by createDate desc")
    List<OrderDetail> getOrderDetailOrderByCreateDate();

    @Query("select c from OrderDetail c where c.done = false and c.acceptance = false order by createDate desc")
    List<OrderDetail> getOrderDetailSale();

    @Query("select c from OrderDetail c where c.done = false and c.acceptance = true order by createDate desc")
    List<OrderDetail> getOrderDetailSaleTrue();

    @Query("select c from OrderDetail c where c.done = false and c.acceptance = true order by createDate desc")
    List<OrderDetail> getOrderAdmin();

    @Query("select c from OrderDetail c where c.done = true and c.acceptance = true order by createDate desc")
    List<OrderDetail> getOrderDetailHistory();

    @Query("select c from OrderDetail c where c.done = false order by createDate desc")
    List<OrderDetail> getListOrderDetail();

    @Query("select c from OrderDetail c where c.createDate >= ?1 and c.createDate <= ?2 and c.status = false")
    List<OrderDetail> getListOrderDetailTotal(Date startDate, Date endDate);

    @Query("select c from OrderDetail c where c.status = false")
    List<OrderDetail> getListTotal();
}
