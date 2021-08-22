package com.example.cs_office.Repository;

import com.example.cs_office.Model.Entity.Schedule;
import com.example.cs_office.Model.Entity.Scheduledetail;
import com.example.cs_office.Model.Entity.ServiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceDetailRepository extends JpaRepository<ServiceDetail,Integer> {
    //select ServiceDetail by id
    @Query("select s from ServiceDetail s where s.id = ?1")
    Optional<ServiceDetail> findServiceDetailById(Integer id);

    //select ServiceDetail by status
    @Query("select c from ServiceDetail c where c.status = ?1")
    List<ServiceDetail> findServiceDetailByStatus(boolean status);

    @Query("select c from ServiceDetail c where c.schedule.id = ?1 and c.acceptance = false")
    List<ServiceDetail> getListIdServiceDetailByIdSchedule(int idSchedule);

    @Query("select c from ServiceDetail c where c.schedule.id = ?1")
    List<ServiceDetail> getListIdServiceDetailByIdSchedule2(int idSchedule);

    @Query("select c from ServiceDetail c where c.schedule.id = ?1 and c.status = true")
    List<ServiceDetail> getListIdServiceDetailByIdSchedule1(int idSchedule);

    @Query("select c from ServiceDetail c where c.schedule.id = ?1")
    List<ServiceDetail> getServiceDetailByIdSchedule(int idSchedule);

    //update acceptance = true by id idSchedule
    @Transactional
    @Modifying
    @Query("update ServiceDetail c set c.acceptance = true where c.schedule.id = :idSchedule")
    int updateServiceDetailByIdSchedule(@Param("idSchedule") int idSchedule);

    @Transactional
    @Modifying
    @Query("delete from ServiceDetail c where c.schedule.id = :idSchedule")
    int deleteServiceDetailByIdSchedule(@Param("idSchedule") int idSchedule);
}
