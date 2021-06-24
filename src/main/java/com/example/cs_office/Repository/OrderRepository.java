package com.example.cs_office.Repository;

import com.example.cs_office.Model.Evaluate;
import com.example.cs_office.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    //select order by id
    @Query("select s from Order s where s.id = ?1")
    Optional<Order> findOrderById(Integer id);

    //select order by status
    @Query("select c from Order c where c.status = ?1")
    List<Order> findOrderByStatus(boolean status);
}
