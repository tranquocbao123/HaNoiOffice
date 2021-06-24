package com.example.cs_office.Repository;

import com.example.cs_office.Model.Role;
import com.example.cs_office.Model.TypeRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    //select role by id
    @Query("select s from Role s where s.id = ?1")
    Optional<Role> findRoleById(Integer id);
    // timf kiem theo name
    @Query("select s from Role s where s.name Like %:name%")
    public List<Role> findByname(@PathParam("name")String name);
    //select role by status
    @Query("select c from Role c where c.status = ?1")
    List<Role> findRoleByStatus(boolean status);
    
}
