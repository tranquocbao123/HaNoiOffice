package com.example.cs_office.Repository;

import com.example.cs_office.Model.Evaluate;
import com.example.cs_office.Model.PriceService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluateRepository extends JpaRepository<Evaluate,Integer> {
    //select evaluate by id
    @Query("select s from Evaluate s where s.id = ?1")
    Optional<Evaluate> findEvaluateById(Integer id);

    //select evaluate by status
    @Query("select c from Evaluate c where c.status = ?1")
    List<Evaluate> findEvaluateByStatus(boolean status);

}
