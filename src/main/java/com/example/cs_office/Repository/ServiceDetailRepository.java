package com.example.cs_office.Repository;

import com.example.cs_office.Model.Entity.ServiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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

}
