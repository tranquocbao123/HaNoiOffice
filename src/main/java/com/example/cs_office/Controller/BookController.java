package com.example.cs_office.Controller;

import com.example.cs_office.Model.InFoRoom.InFoRoomByStartEndTypeRomBranch;
import com.example.cs_office.Model.RoomBook.RoomBookLT;
import com.example.cs_office.Model.Dto.CheckRoom;
import com.example.cs_office.Model.Dto.RoomBook;
import com.example.cs_office.Model.Dto.RoomCustomer;
import com.example.cs_office.Model.Entity.Room;
import com.example.cs_office.Model.RoomBook.RoomBookKLT;
import com.example.cs_office.Model.Search.SearchRoom;
import com.example.cs_office.Service.*;
import com.example.cs_office.Util.PathResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PathResources.BOOK)
@CrossOrigin("*")
public class BookController {

    private RoomService roomService;

    private BookService bookService;

    private OrderDetailService orderDetailService;

    @Autowired
    public BookController(RoomService roomService, BookService bookService, OrderDetailService orderDetailService) {
        this.roomService = roomService;
        this.bookService = bookService;
        this.orderDetailService = orderDetailService;
    }

    //get list room by id type room and id branch
    @GetMapping(PathResources.LISTROOM)
    public List<Room> getListRoomByTypeRoomAndBranch(@PathVariable("idTypeRoom") String idTypeRoom,
                                                     @PathVariable("idBranch") String idBranch) {
        return roomService.getListRoomByTypeRoomAndBranch(idTypeRoom, idBranch);
    }

    //get list room by id type room and id branch
    @GetMapping(PathResources.TEST)
    public List<Room> getTest(@Param("idTypeRoom") String idTypeRoom,
                              @Param("idBranch") String idBranch) {
        return roomService.getListRoomByTypeRoomAndBranch(idTypeRoom, idBranch);
    }

    //book room lt not accept
    @PostMapping(PathResources.BOOKROOMLT)
    public boolean bookRoomNotAccept(@RequestBody RoomBookLT roomBookLT) {
        return bookService.bookRoomLTNotAccept(roomBookLT);
    }

    //book roon klt not accept
    @PostMapping(PathResources.BOOKROOMKTL)
    public boolean roomBookKLT(@RequestBody RoomBookKLT roomBookKLT) {
        return bookService.bookRookKLTNotAccept(roomBookKLT);
    }

    @PutMapping(PathResources.ACCEPT)
    public int acceptRoomBook(@PathVariable int idOrderDetail) {
        return bookService.acceptBookRoom(idOrderDetail);
    }

    @DeleteMapping(PathResources.CANCEL)
    public int cancelRoomBook(@PathVariable int idOrderDetail) {
        return bookService.cancelBookRoom(idOrderDetail);
    }

    @GetMapping(PathResources.LISTIFBYENDSTART)
    public List<InFoRoomByStartEndTypeRomBranch> list (@RequestBody SearchRoom searchRoom) {
        return bookService.getListInfoRoom(searchRoom);
    }

    @GetMapping(PathResources.CHECKROOMBYID)
    public CheckRoom checkRoomById(@PathVariable int idRoom) {
        return orderDetailService.getListOrderDetailByIdRoom(idRoom);
    }

    @GetMapping(PathResources.LISTBOOKROOM)
    public List<RoomBook> listRoomBook() {
        return orderDetailService.getListRoomBook();
    }

    @GetMapping(PathResources.LISTROOMSALE)
    public List<CheckRoom> listRoomSale(@PathVariable("idTypeRoom") String idTypeRoom,
                                        @PathVariable("idBranch") String idBranch) {
        return orderDetailService.listRoomSale(idTypeRoom, idBranch);
    }

    @GetMapping(PathResources.ROOKBOOK)
    public List<RoomCustomer> listRoomByIdCustomer(@PathVariable("idCustomer") int idCustomer) {
        return orderDetailService.listRoomBook(idCustomer);
    }

    @GetMapping(PathResources.TOTALBYIDORDETAIL)
    public double totalByIdOrderDetail(@Param("idOrderDetail") int idOrderDetail) {
        return orderDetailService.getTotalByIdOrderDetail(idOrderDetail);
    }

    @GetMapping(PathResources.TOTAL)
    public double getTotal() {
        return orderDetailService.getTotal();
    }

}
