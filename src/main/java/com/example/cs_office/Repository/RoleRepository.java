package com.example.cs_office.Repository;

import com.example.cs_office.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    //select role by id
    @Query("select s from Role s where s.id = ?1")
    Optional<Role> findRoleById(Integer id);

    //select role by status
    @Query("select c from Role c where c.status = ?1")
    List<Role> findRoleByStatus(boolean status);

    //select role by name
    @Query("select c from Role c where c.name like %:name% ")
    List<Role> findRoleByName(@Param("name") String name);

}
