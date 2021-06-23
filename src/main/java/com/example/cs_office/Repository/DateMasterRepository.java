package com.example.cs_office.Repository;

import com.example.cs_office.Model.Customer;
import com.example.cs_office.Model.DateMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DateMasterRepository extends JpaRepository<DateMaster, Integer> {
    //select datemaster by id
    @Query("select s from DateMaster s where s.id = ?1")
    Optional<DateMaster> findDateMasterById(Integer id);

    //select datemaster by status
    @Query("select c from DateMaster c where c.status = ?1")
    List<DateMaster> findDateMasterByStatus(boolean status);


}
