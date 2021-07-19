package com.example.cs_office.Repository;

import com.example.cs_office.Model.Entity.Customer;
import com.example.cs_office.Model.Entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
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

    //select staff by email
    @Query("select c from Staff c where c.email = ?1")
    Staff findStaffByEmail(String email);

    //stored procedure get_list_staff
    @Query(value = "{CALL get_list_staff(:id_Role, :id_Branch, :GENDER, :pNameStaff)}" , nativeQuery = true)
    List<Staff> get_list_staff(@Param("id_Role") String idRole,
                               @Param("id_Branch") String idBranch,
                               @Param("GENDER") boolean gender,
                               @Param("pNameStaff") String nameStaff);
}
