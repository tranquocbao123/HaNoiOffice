package com.example.cs_office.Controller;

import com.example.cs_office.Model.Staff;
import com.example.cs_office.Model.TypeRoom;
import com.example.cs_office.Service.TypeRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/typeroom")
@CrossOrigin("*")
public class TypeRoomController {
    @Autowired
    private final TypeRoomService typeRoomService;

    public TypeRoomController(TypeRoomService typeRoomService) {
        this.typeRoomService = typeRoomService;
    }

    //list type room
    @GetMapping()
    public List<TypeRoom> getTypeRoom() {
        return typeRoomService.getTypeRoom();
    }

    //list type room by status == fasle
    @GetMapping("/false")
    public List<TypeRoom> getTypeRoomByStatusFalse() {
        return typeRoomService.getTypeRoomByStatus(false);
    }

    //list type room by status == true
    @GetMapping("/true")
    public List<TypeRoom> getTypeRoomByStatusTrue() {
        return typeRoomService.getTypeRoomByStatus(true);
    }

    // insert type room
    @PostMapping
    public void insertTypeRoom(@RequestBody TypeRoom typeRoom) {
        typeRoomService.addNewTypeRoom(typeRoom);
    }

    //search type room by id
    @GetMapping(path = "{typeroomId}")
    public Optional<TypeRoom> getTypeRoomById(
            @PathVariable("typeroomId") int typeroomId) {
        return typeRoomService.getTypeRoomById(typeroomId);
    }

    //search type room by name
    @GetMapping(path = "searchname/{typeRoomName}")
    public List<TypeRoom> getTypeRoomByName(
            @PathVariable("typeRoomName") String typeRoomName) {
        return typeRoomService.getTypeRoomByName(typeRoomName);
    }

    //delete type room by id
    @DeleteMapping(path = "{typeroomId}")
    public void deleteTypeRoom(
            @PathVariable("typeroomId") int typeroomId) {
        typeRoomService.deleteTypeRoom(typeroomId);
    }

    //upadate type room by id
    @PutMapping(path = "/{typeroomId}")
    public TypeRoom updateTyperoom
    (@RequestBody TypeRoom typeRoom,
     @PathVariable("typeroomId") int id) {
        return typeRoomService.updateTyperoom(typeRoom, id);
    }
}
