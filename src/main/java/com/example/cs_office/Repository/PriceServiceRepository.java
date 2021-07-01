package com.example.cs_office.Repository;

import com.example.cs_office.Model.PriceService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PriceServiceRepository extends JpaRepository<PriceService,Integer> {
    //select priceservice by id
    @Query("select s from PriceService s where s.id = ?1")
    Optional<PriceService> findPriceServiceById(Integer id);

    //select priceservice by status
    @Query("select c from PriceService c where c.status = ?1")
    List<PriceService> findPriceServiceByStatus(boolean status);

}
