package com.example.cs_office.Controller;

import com.example.cs_office.Model.PriceTypeRoom;
import com.example.cs_office.Model.Room;
import com.example.cs_office.Service.PriceTypeRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/pricetyperoom")
@CrossOrigin("*")
public class PriceTypeRoomController {
    @Autowired
    private final PriceTypeRoomService priceTypeRoomService;

    public PriceTypeRoomController(PriceTypeRoomService priceTypeRoomService) {
        this.priceTypeRoomService = priceTypeRoomService;
    }


    //list price type room
    @GetMapping()
    public List<PriceTypeRoom> getPriceTypeRoom() {
        return priceTypeRoomService.getPriceTypeRoom();
    }

    //list price type room by status == fasle
    @GetMapping("/false")
    public List<PriceTypeRoom> getPriceTypeRoomByStatusFalse() {
        return priceTypeRoomService.getPriceTypeRoomByStatus(false);
    }

    //list price type room by status == true
    @GetMapping("/true")
    public List<PriceTypeRoom> getPriceTypeRoomByStatusTrue() {
        return priceTypeRoomService.getPriceTypeRoomByStatus(true);
    }

    // insert price type room
    @PostMapping
    public void insertStaff(@RequestBody PriceTypeRoom priceTypeRoom) {
        priceTypeRoomService.addNewPriceTypeRoom(priceTypeRoom);
    }

    //search price type room by id
    @GetMapping(path = "{pricetyperoomId}")
    public Optional<PriceTypeRoom> getPriceTypeRoomById(
            @PathVariable("pricetyperoomId") int pricetyperoomId) {
        return priceTypeRoomService.getPriceTypeRoomById(pricetyperoomId);
    }

    //delete price type room by id
    @DeleteMapping(path = "{pricetyperoomId}")
    public void deletePriceTypeRoom(
            @PathVariable("pricetyperoomId") int pricetyperoomId) {
        priceTypeRoomService.deletePriceTypeRoom(pricetyperoomId);
    }

    //update price type room by id

    @PutMapping(path = "/{priceTypeRoomId}")
    public PriceTypeRoom updatePriceTypeRoom
            (@RequestBody PriceTypeRoom priceTypeRoom,
             @PathVariable("priceTypeRoomId") int id) {
        return priceTypeRoomService.updatePriceTypeRoom(priceTypeRoom, id);
    }
}
