package com.example.cs_office.Repository;

import com.example.cs_office.Model.Entity.Schedule;
import com.example.cs_office.Model.Entity.Scheduledetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleDetailRepository extends JpaRepository<Scheduledetail,Integer> {
    //select schedule detail by id
    @Query("select s from Scheduledetail s where s.id = ?1")
    Optional<Scheduledetail> findScheduledetailById(Integer id);

    //select schedule detail by datePresent
    @Query("select s from Scheduledetail s where s.datePresent = ?1")
    List<Scheduledetail> getScheduledetailByDate(Date datePresent);

    //select schedule detail by status
    @Query("select c from Scheduledetail c where c.status = ?1")
    List<Scheduledetail> findScheduledetailByStatus(boolean status);

    //update acceptance = true by id idSchedule
    @Transactional
    @Modifying
    @Query("update Scheduledetail c set c.acceptance = true where c.schedule.id = :idSchedule")
    int updateScheduleByIdSchedule(@Param("idSchedule") int idSchedule);

    //update inout = false by id idScheduleDetail
    @Transactional
    @Modifying
    @Query("update Scheduledetail c set c.checkinout = false where c.id = :idScheduleDetail")
    int updateScheduleById(@Param("idScheduleDetail") int idScheduleDetail);

    @Query("select c from Scheduledetail c where c.schedule.id = ?1 and c.acceptance = false")
    List<Scheduledetail> getListIdScheduleByIdSchedule(int idSchedule);

    @Query("select c from Scheduledetail c where c.schedule.id = ?1")
    List<Scheduledetail> getScheduleByIdSchedule(int idSchedule);

    @Query("select c from Scheduledetail c where c.schedule.id = ?1 and c.datePresent =?2")
    List<Scheduledetail> getScheduleByIdScheduleAndDatePresent(int idSchedule, Date datePresent);

    @Query("select c from Scheduledetail c where c.schedule.id = ?1 and c.datePresent = ?2 and c.status = true")
    List<Scheduledetail> getListIdScheduleByIdSchedule1(int Schedule, Date startDate);

    @Transactional
    @Modifying
    @Query("delete from Scheduledetail c where c.schedule.id = :idSchedule")
    int deleteScheduleDetailByIdSchedule(@Param("idSchedule") int idSchedule);

}
