package com.example.cs_office.Repository;

import com.example.cs_office.Model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Service,Integer> {
    //select service by id
    @Query("select s from Service s where s.id = ?1")
    Optional<Service> findServiceById(Integer id);

    //select service by status
    @Query("select c from Service c where c.status = ?1")
    List<Service> findServiceByStatus(boolean status);

    //select service by name
    @Query("select c from Service c where c.name like %:name% ")
    List<Service> findServiceByName(@Param("name") String name);

}
