package com.example.cs_office.Repository;

import com.example.cs_office.Model.Dto.CheckRoom;
import com.example.cs_office.Model.Entity.Room;
import com.example.cs_office.Model.Entity.Staff;
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
    Room findRoomById(Integer id);

    //select room by status
    @Query("select c from Room c where c.status = ?1")
    List<Room> findRoomByStatus(boolean status);

    //select room by name
    @Query("select c from Room c where c.name like %:name% ")
    List<Room> findRoomByName(@Param("name") String name);

    //stored procedure get_list_room_by_typeroom_branch
    @Query(value = "{CALL get_list_room_by_typeroom_branch(:id_TypeRoom, :id_Branch)}" , nativeQuery = true)
    List<Room> getListRoomByTypeRoomAndBranch(@Param("id_TypeRoom") String idTypeRoom,
                               @Param("id_Branch") String idBranch);

    @Query(value = "{CALL get_list_room(:id_TypeRoom, :id_Branch, :min, :max)}" , nativeQuery = true)
    List<Room> getRoomBySo(@Param("id_TypeRoom") int idTypeRoom,
                           @Param("id_Branch") int idBranch,
                           @Param("min") int min,
                           @Param("max") int max);
}
