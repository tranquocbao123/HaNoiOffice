package com.example.cs_office.Repository;

import com.example.cs_office.Model.Entity.Schedule;
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
public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {
    //select schedule by id
    @Query("select s from Schedule s where s.id = ?1")
    Optional<Schedule> findScheduleById(Integer id);

    //select schedule by status
    @Query("select c from Schedule c where c.status = ?1")
    List<Schedule> findScheduleByStatus(boolean status);

    //select schedule by id orderdetail
    @Query("select c from Schedule c where c.orderDetail.id = ?1 and c.status = true")
    List<Schedule> getListScheduleByIdOrderDetail(int idOrderDetail);

    //select schedule by id orderdetail
    @Query("select c from Schedule c where c.orderDetail.id = ?1")
    List<Schedule> getListIdScheduleByIdOrderDetail(int idOrderDetail);

    @Query("select c from Schedule c where c.orderDetail.id = ?1")
    List<Schedule> getScheduleByIdOrderDetail(int idOrderDetail);

    @Query("select c from Schedule c where c.orderDetail.id = ?1 and c.startDate >= ?2 and c.endDate <= ?3")
    List<Schedule> getScheduleBySearchSale(int idOrderDetail, Date startDate, Date endDate);

    @Query("select c from Schedule c where c.orderDetail.id = ?1 and c.endDate < ?2")
    List<Schedule> getScheduleUpdate(int idOrderDetail, java.util.Date dateNow);

    //select schedule by id orderdetail
    @Query("select c from Schedule c where c.orderDetail.id = ?1 and c.status = true")
    List<Schedule> getListScheduleByIdOrderDetailFalse(int idOrderDetail);

    //select schedule by id orderdetail
    @Query("select c from Schedule c where c.orderDetail.id = ?1 and c.status = true")
    List<Schedule> getListScheduleByIdOrderDetailCustomer(int idOrderDetail);

    @Transactional
    @Modifying
    @Query("delete from Schedule c where c.orderDetail.id = :idOrderDetail")
    int deleteScheduleByIdOrderDetail(@Param("idOrderDetail") int idOrderDetail);

    @Transactional
    @Modifying
    @Query("update Schedule c set c.status = false where c.orderDetail.id = :idOrderDetail")
    int updateStatusByIdOrderDetail(@Param("idOrderDetail") int idOrderDetail);

    //select schedule by enddate = ? and startdate = ?
    @Query("select c from Schedule c where c.endDate <= :endDate and c.startDate >= :startDate and c.orderDetail.id = :idOrderDetail")
    List<Schedule> listScheduleByEndStart(@Param("endDate") Date endDate, @Param("startDate") Date startDate, @Param("idOrderDetail") int idOrderDetail);

    //select schedule by id orderdetail
    @Query("select c from Schedule c where c.orderDetail.id = ?1 and c.status = true")
    List<Schedule> getListIdScheduleByIdOrderDetail1(int idOrderDetail);

    //select schedule by id orderdetail
    @Query("select c from Schedule c where c.orderDetail.id = ?1")
    List<Schedule> getListIdScheduleByIdOrderDetail2(int idOrderDetail);
}
