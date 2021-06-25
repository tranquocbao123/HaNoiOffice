package com.example.cs_office.Repository;

import com.example.cs_office.Model.OrderHistory;
import com.example.cs_office.Model.PriceTypeRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PriceTypeRoomRepository extends JpaRepository<PriceTypeRoom,Integer> {

    //select price type room by id
    @Query("select s from PriceTypeRoom s where s.id = ?1")
    Optional<PriceTypeRoom> findPriceTypeRoomById(Integer id);

    //select price type room by status
    @Query("select c from PriceTypeRoom c where c.status = ?1")
    List<PriceTypeRoom> findPriceTypeRoomByStatus(boolean status);

}
