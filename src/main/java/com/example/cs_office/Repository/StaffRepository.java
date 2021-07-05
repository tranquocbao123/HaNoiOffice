package com.example.cs_office.Repository;

import com.example.cs_office.Model.Entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    //select staff by id
    @Query("select s from Staff s where s.id = ?1")
    Optional<Staff> findStaffById(Integer id);

    //select staff by status
    @Query("select c from Staff c where c.status = ?1")
    List<Staff> findStaffByStatus(boolean status);

    //select staff by name
    @Query("select c from Staff c where c.userName like %:username% ")
    List<Staff> findStaffByUserName(@Param("username") String username);

    //select staff by idRole,idBranch
//    @Query(select s from Staff s where s.idBranch = :idBranch and s.idRole = 1)

}
