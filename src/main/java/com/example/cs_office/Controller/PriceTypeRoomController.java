package com.example.cs_office.Controller;

import com.example.cs_office.Model.Entity.PriceTypeRoom;
import com.example.cs_office.Service.PriceTypeRoomService;
import com.example.cs_office.Util.PathResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(PathResources.PRICETYPEROOM)
@CrossOrigin("*")
public class PriceTypeRoomController {
    @Autowired
    private final PriceTypeRoomService priceTypeRoomService;

    public PriceTypeRoomController(PriceTypeRoomService priceTypeRoomService) {
        this.priceTypeRoomService = priceTypeRoomService;
    }


    //list price type room
    @GetMapping(PathResources.FIND_ALL)
    public List<PriceTypeRoom> getPriceTypeRoom() {
        return priceTypeRoomService.getPriceTypeRoom();
    }

    //list price type room by status == fasle
    @GetMapping(PathResources.FIND_BY_STATUSFALSE)
    public List<PriceTypeRoom> getPriceTypeRoomByStatusFalse() {
        return priceTypeRoomService.getPriceTypeRoomByStatus(false);
    }

    //list price type room by status == true
    @GetMapping(PathResources.FIND_BY_STATUSTRUE)
    public List<PriceTypeRoom> getPriceTypeRoomByStatusTrue() {
        return priceTypeRoomService.getPriceTypeRoomByStatus(true);
    }

    // insert price type room
    @PostMapping(PathResources.SAVE)
    public void insertStaff(@RequestBody PriceTypeRoom priceTypeRoom) {
        priceTypeRoomService.addNewPriceTypeRoom(priceTypeRoom);
    }

    //search price type room by id
    @GetMapping(path = PathResources.FIND_BY_ID)
    public Optional<PriceTypeRoom> getPriceTypeRoomById(
            @PathVariable("id") int pricetyperoomId) {
        return priceTypeRoomService.getPriceTypeRoomById(pricetyperoomId);
    }

    //delete price type room by id
    @DeleteMapping(path = PathResources.DELETEBYID)
    public void deletePriceTypeRoom(
            @PathVariable("id") int pricetyperoomId) {
        priceTypeRoomService.deletePriceTypeRoom(pricetyperoomId);
    }

    //update price typeroom by status
    @PutMapping(PathResources.UPDATESTATUSFALSE)
    public void updatePriceTypeRoomStatus(
            @RequestBody PriceTypeRoom priceTypeRoom
    ) {
        priceTypeRoomService.updatePriceTypeRoomStatus(priceTypeRoom);
    }

    //update price typeroom black by status
    @PutMapping(path = PathResources.UPDATESTATUSTRUE)
    public void updatePriceTypeRoomBlack(
            @RequestBody PriceTypeRoom priceTypeRoom
    ) {
        priceTypeRoomService.updatePriceTypeRoomBlack(priceTypeRoom);
    }

    //update price type room by id

    @PutMapping(path = PathResources.UPDATEBYID)
    public PriceTypeRoom updatePriceTypeRoom
            (@RequestBody PriceTypeRoom priceTypeRoom,
             @PathVariable("id") int priceTypeRoomId) {
        return priceTypeRoomService.updatePriceTypeRoom(priceTypeRoom, priceTypeRoomId);
    }

}
