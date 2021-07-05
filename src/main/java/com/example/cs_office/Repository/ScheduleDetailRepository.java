package com.example.cs_office.Repository;

import com.example.cs_office.Model.Entity.Scheduledetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleDetailRepository extends JpaRepository<Scheduledetail,Integer> {
    //select schedule detail by id
    @Query("select s from Scheduledetail s where s.id = ?1")
    Optional<Scheduledetail> findScheduledetailById(Integer id);

    //select schedule detail by status
    @Query("select c from Scheduledetail c where c.status = ?1")
    List<Scheduledetail> findScheduledetailByStatus(boolean status);

}
