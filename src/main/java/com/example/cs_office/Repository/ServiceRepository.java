package com.example.cs_office.Repository;

import com.example.cs_office.Model.Service;
import com.example.cs_office.Model.TypeRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Service,Integer> {
    //select service by id
    @Query("select s from Service s where s.id = ?1")
    Optional<Service> findServiceById(Integer id);
    @Query("select s from Service s where s.name Like %:name%")
    public List<Service> findservice(@PathParam("name")String name);
    //select service by status
    @Query("select c from Service c where c.status = ?1")
    List<Service> findServiceByStatus(boolean status);
}
