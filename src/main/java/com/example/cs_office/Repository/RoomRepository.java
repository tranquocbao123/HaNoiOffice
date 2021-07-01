package com.example.cs_office.Repository;

import com.example.cs_office.Model.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer> {

    //select room by id
    @Query("select s from Room s where s.id = ?1")
    Optional<Room> findRoomById(Integer id);

    //select room by status
    @Query("select c from Room c where c.status = ?1")
    List<Room> findRoomByStatus(boolean status);

    //select room by name
    @Query("select c from Room c where c.name like %:name% ")
    List<Room> findRoomByName(@Param("name") String name);

}
