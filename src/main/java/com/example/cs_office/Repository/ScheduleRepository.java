package com.example.cs_office.Repository;

import com.example.cs_office.Model.Entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {
    //select schedule by id
    @Query("select s from Schedule s where s.id = ?1")
    Optional<Schedule> findScheduleById(Integer id);

    //select schedule by status
    @Query("select c from Schedule c where c.status = ?1")
    List<Schedule> findScheduleByStatus(boolean status);

}
