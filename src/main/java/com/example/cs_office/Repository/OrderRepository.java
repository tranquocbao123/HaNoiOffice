package com.example.cs_office.Repository;

import com.example.cs_office.Model.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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

}
