package com.example.cs_office.Controller;


import com.example.cs_office.Model.Branch;
import com.example.cs_office.Model.Role;
import com.example.cs_office.Model.Room;
import com.example.cs_office.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
@CrossOrigin("*")
public class RoomController {
    @Autowired
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    //list room
    @GetMapping()
    public List<Room> getRoom() {
        return roomService.getRoom();
    }

    //list room by status == fasle
    @GetMapping("/false")
    public List<Room> getRoomByStatusFalse() {
        return roomService.getRoomByStatus(false);
    }

    //list room by status == true
    @GetMapping("/true")
    public List<Room> getRoomByStatusTrue() {
        return roomService.getRoomByStatus(true);
    }

    // insert room
    @PostMapping
    public void insertRoom(@RequestBody Room room) {
        roomService.addNewRoom(room);
    }

    //search room by id
    @GetMapping(path = "{roomId}")
    public Optional<Room> getRoomById(
            @PathVariable("roomId") int roomId) {
        return roomService.getRoomById(roomId);
    }

    //search room by name
    @GetMapping(path = "searchname/{roomName}")
    public List<Room> getRoomByName(
            @PathVariable("roomName") String roomName) {
        return roomService.getRoomByName(roomName);
    }

    //delete room by id
    @DeleteMapping(path = "{roomId}")
    public void deleteRoom(
            @PathVariable("roomId") int roomId) {
        roomService.deleteRoom(roomId);
    }
    //update room by id
    @PutMapping(path = "/{roomId}")
    public Room updateRoom
    (@RequestBody Room room,
     @PathVariable("roomId") int id) {
        return roomService.updateRoom(room, id);
    }
}
