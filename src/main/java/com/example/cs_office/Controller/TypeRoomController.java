package com.example.cs_office.Controller;

import com.example.cs_office.Model.Staff;
import com.example.cs_office.Model.TypeRoom;
import com.example.cs_office.Service.TypeRoomService;
import com.example.cs_office.Util.PathResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(PathResources.TYPEROOM)
@CrossOrigin("*")
public class TypeRoomController {
    @Autowired
    private final TypeRoomService typeRoomService;

    public TypeRoomController(TypeRoomService typeRoomService) {
        this.typeRoomService = typeRoomService;
    }

    //list type room
    @GetMapping(PathResources.FIND_ALL)
    public List<TypeRoom> getTypeRoom() {
        return typeRoomService.getTypeRoom();
    }

    //list type room by status == fasle
    @GetMapping(PathResources.FIND_BY_STATUSFALSE)
    public List<TypeRoom> getTypeRoomByStatusFalse() {
        return typeRoomService.getTypeRoomByStatus(false);
    }

    //list type room by status == true
    @GetMapping(PathResources.FIND_BY_STATUSTRUE)
    public List<TypeRoom> getTypeRoomByStatusTrue() {
        return typeRoomService.getTypeRoomByStatus(true);
    }

    // insert type room
    @PostMapping(PathResources.SAVE)
    public void insertTypeRoom(@RequestBody TypeRoom typeRoom) {
        typeRoomService.addNewTypeRoom(typeRoom);
    }

    //search type room by id
    @GetMapping(path = PathResources.FIND_BY_ID)
    public Optional<TypeRoom> getTypeRoomById(
            @PathVariable("id") int typeroomId) {
        return typeRoomService.getTypeRoomById(typeroomId);
    }

    //search type room by name
    @GetMapping(path = PathResources.FIND_BY_NAME)
    public List<TypeRoom> getTypeRoomByName(
            @PathVariable("name") String typeRoomName) {
        return typeRoomService.getTypeRoomByName(typeRoomName);
    }

    //delete type room by id
    @DeleteMapping(path = PathResources.DELETEBYID)
    public void deleteTypeRoom(
            @PathVariable("id") int typeroomId) {
        typeRoomService.deleteTypeRoom(typeroomId);
    }

    //update type room by status
    @PutMapping(PathResources.UPDATESTATUSFALSE)
    public void updateTypeRoomStatus(
            @RequestBody TypeRoom typeRoom
    ) {
        typeRoomService.updateTypeRoomStatus(typeRoom);
    }

    //update type room black by status
    @PutMapping(path = PathResources.UPDATESTATUSTRUE)
    public void updateTypeRoomBlack(
            @RequestBody TypeRoom typeRoom
    ) {
        typeRoomService.updateTypeRoomBlack(typeRoom);
    }

    //upadate type room by id
    @PutMapping(path = PathResources.UPDATEBYID)
    public TypeRoom updateTyperoom
    (@RequestBody TypeRoom typeRoom,
     @PathVariable("id") int typeRoomId) {
        return typeRoomService.updateTyperoom(typeRoom, typeRoomId);
    }

}
