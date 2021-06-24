package com.example.cs_office.Repository;

import com.example.cs_office.Model.PriceTypeRoom;
import com.example.cs_office.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer> {

    //select room by id
    @Query("select s from Room s where s.id = ?1")
    Optional<Room> findRoomById(Integer id);
@Query("select s from Room s where s.name LIKE %:name%")
List<Room> findByRoom(@PathParam("name")String name);
    //select room by status
    @Query("select c from Room c where c.status = ?1")
    List<Room> findRoomByStatus(boolean status);
}
