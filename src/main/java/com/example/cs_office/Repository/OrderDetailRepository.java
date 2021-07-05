package com.example.cs_office.Repository;


import com.example.cs_office.Model.Entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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

}
