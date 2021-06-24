package com.example.cs_office.Repository;

import com.example.cs_office.Model.TypeRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@Repository
public interface TypeRoomRepository extends JpaRepository<TypeRoom,Integer> {
    //select Type Room by id
    @Query("select s from TypeRoom s where s.id = ?1")
    Optional<TypeRoom> findTypeRoomById(Integer id);

//    @Query("select s from TypeRoom s where s.name = ?1")
//    Optional<TypeRoom> findTypeRoomByName(String name);

//    public List<TypeRoom> findByname(String name);
  @Query("select s from TypeRoom s where s.name Like %:name%")
    public List<TypeRoom> findByname(@PathParam("name")String name);

    //select ServiceDetail by status
    @Query("select c from TypeRoom c where c.status = ?1")
    List<TypeRoom> findTypeRoomByStatus(boolean status);
//

}
