package com.example.cs_office.Repository;

import com.example.cs_office.Model.Entity.DateMasters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DateMasterRepository extends JpaRepository<DateMasters, Integer> {
    //select datemaster by id
    @Query("select s from DateMasters s where s.id = ?1")
    Optional<DateMasters> findDateMasterById(Integer id);

    //select datemaster by status
    @Query("select c from DateMasters c where c.status = ?1")
    List<DateMasters> findDateMasterByStatus(boolean status);

}
