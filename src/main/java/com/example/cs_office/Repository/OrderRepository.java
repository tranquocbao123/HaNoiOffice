package com.example.cs_office.Repository;

import com.example.cs_office.Model.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Integer> {

    //select order by id
    @Query("select s from Orders s where s.id = ?1")
    Optional<Orders> findOrderById(Integer id);

    //select order by status
    @Query("select c from Orders c where c.status = ?1")
    List<Orders> findOrderByStatus(boolean status);

    //select order by id Customer
    @Query("select c from Orders c where c.customer.id = ?1")
    List<Orders> findOrderByIdCustomer(@Param("idCustomer") int idCustomer);

    //select order by status == true and acceptance = false
    @Query("select c from Orders c where c.status = true")
    List<Orders> findOrderByStatusAndAcceptance();

    //select order by status == true and acceptance = true and id customer
    @Query("select c from Orders c where c.status = true and c.customer.id = :idCustomer ")
    List<Orders> listOrderDetailByIdCustomer(@Param("idCustomer") int idCustomer);

    @Transactional
    @Modifying
    @Query("update Orders c set c.status = false where c.id = :idOrder")
    int updateStatusByIdOrderDetail(@Param("idOrder") int idOrder);
}
