package com.example.cs_office.Repository;

import com.example.cs_office.Model.TypeRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeRoomRepository extends JpaRepository<TypeRoom,Integer> {
    //select Type Room by id
    @Query("select s from TypeRoom s where s.id = ?1")
    Optional<TypeRoom> findTypeRoomById(Integer id);

    //select Type Room by status
    @Query("select c from TypeRoom c where c.status = ?1")
    List<TypeRoom> findTypeRoomByStatus(boolean status);

    //select Type Room by name
    @Query("select c from TypeRoom c where c.name like %:name% ")
    List<TypeRoom> findTypeRoomByName(@Param("name") String name);

}
