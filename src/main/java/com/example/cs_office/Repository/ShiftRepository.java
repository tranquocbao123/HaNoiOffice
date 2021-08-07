package com.example.cs_office.Repository;

import com.example.cs_office.Model.Entity.Service;
import com.example.cs_office.Model.Entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShiftRepository extends JpaRepository<Shift,Integer> {

    //select service by id
    @Query("select s from Shift s where s.id = ?1")
    Optional<Shift> findShiftById(Integer id);

    //select service by status
    @Query("select c from Shift c where c.status = ?1")
    List<Shift> findShiftByStatus(boolean status);

}
