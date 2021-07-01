package com.example.cs_office.Controller;


import com.example.cs_office.Model.Entity.Room;
import com.example.cs_office.Service.RoomService;
import com.example.cs_office.Util.PathResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(PathResources.ROOM)
@CrossOrigin("*")
public class RoomController {
    @Autowired
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    //list room
    @GetMapping(PathResources.FIND_ALL)
    public List<Room> getRoom() {
        return roomService.getRoom();
    }

    //list room by status == fasle
    @GetMapping(PathResources.FIND_BY_STATUSFALSE)
    public List<Room> getRoomByStatusFalse() {
        return roomService.getRoomByStatus(false);
    }

    //list room by status == true
    @GetMapping(PathResources.FIND_BY_STATUSTRUE)
    public List<Room> getRoomByStatusTrue() {
        return roomService.getRoomByStatus(true);
    }

    // insert room
    @PostMapping(PathResources.SAVE)
    public void insertRoom(@RequestBody Room room) {
        roomService.addNewRoom(room);
    }

    //search room by id
    @GetMapping(path = PathResources.FIND_BY_ID)
    public Optional<Room> getRoomById(
            @PathVariable("id") int roomId) {
        return roomService.getRoomById(roomId);
    }

    //search room by name
    @GetMapping(path = PathResources.FIND_BY_NAME)
    public List<Room> getRoomByName(
            @PathVariable("name") String roomName) {
        return roomService.getRoomByName(roomName);
    }

    //delete room by id
    @DeleteMapping(path = PathResources.DELETEBYID)
    public void deleteRoom(
            @PathVariable("id") int roomId) {
        roomService.deleteRoom(roomId);
    }

    //update room by status
    @PutMapping(PathResources.UPDATESTATUSFALSE)
    public void updateRoomStatus(
            @RequestBody Room room
    ) {
        roomService.updateRoomStatus(room);
    }

    //update room black by id
    @PutMapping(path = PathResources.UPDATESTATUSTRUE)
    public void updateRoomBlack(
            @RequestBody Room room
    ) {
        roomService.updateRoomBlack(room);
    }

    //update room by id
    @PutMapping(path = PathResources.UPDATEBYID)
    public Room updateRoom
    (@RequestBody Room room,
     @PathVariable("id") int roomId) {
        return roomService.updateRoom(room, roomId);
    }

}
