package com.example.cs_office.Repository;

import com.example.cs_office.Model.Branch;
import com.example.cs_office.Model.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory,Integer> {
    //select order history by id
    @Query("select s from OrderHistory s where s.id = ?1")
    Optional<OrderHistory> findOrderHistoryById(Integer id);

    //select order history by status
    @Query("select c from OrderHistory c where c.status = ?1")
    List<OrderHistory> findOrderHistoryByStatus(boolean status);

}
