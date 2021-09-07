package com.example.cs_office.Controller;

import com.example.cs_office.Model.Dto.RoomBookCustomer;
import com.example.cs_office.Model.InFoRoom.InFoRoomByStartEndTypeRomBranch;
import com.example.cs_office.Model.RoomBook.RoomBookAccecpSale;
import com.example.cs_office.Model.RoomBook.RoomBookLT;
import com.example.cs_office.Model.Dto.CheckRoom;
import com.example.cs_office.Model.Dto.RoomBook;
import com.example.cs_office.Model.Dto.RoomCustomer;
import com.example.cs_office.Model.Entity.Room;
import com.example.cs_office.Model.RoomBook.RoomBookKLT;
import com.example.cs_office.Model.RoomBook.RoomBookLeTan;
import com.example.cs_office.Model.Search.SearchRoom;
import com.example.cs_office.Model.Search.SearchRoomSale;
import com.example.cs_office.Service.*;
import com.example.cs_office.Util.MessageReponse;
import com.example.cs_office.Util.PathResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
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
    public MessageReponse bookRoomNotAccept(@RequestBody RoomBookLT roomBookLT) {
        return bookService.bookRoomLTNotAccept(roomBookLT);
    }

    //book roon klt not accept
    @PostMapping(PathResources.BOOKROOMKTL)
    public MessageReponse roomBookKLT(@RequestBody RoomBookKLT roomBookKLT) {
        return bookService.bookRookKLTNotAccept(roomBookKLT);
    }

    @PutMapping(PathResources.ACCEPT)
    public MessageReponse acceptRoomBook(@PathVariable int idOrderDetail) {
        return bookService.acceptBookRoom(idOrderDetail);
    }

    @DeleteMapping(PathResources.CANCEL)
    public MessageReponse cancelRoomBook(@PathVariable int idOrderDetail) {
        return bookService.cancelBookRoom(idOrderDetail);
    }

    @GetMapping(PathResources.LISTIFBYENDSTART)
    public List<InFoRoomByStartEndTypeRomBranch> list(@RequestBody SearchRoom searchRoom) {
        return bookService.getListInfoRoom(searchRoom);
    }

    @GetMapping(PathResources.CHECKROOMBYID)
    public CheckRoom checkRoomById(@PathVariable int idRoom) {
        return orderDetailService.getListOrderDetailByIdRoom(idRoom);
    }

    // lấy ra danh sách các phòng khách hàng đã đặt
    @GetMapping(PathResources.LISTBOOKROOM)
    public List<RoomBook> listRoomBook() {
        return orderDetailService.getListRoomBook();
    }

    // lấy ra danh sách các phòng khách hàng đã đặt đã được chấp thuận
    @GetMapping(PathResources.LISTBOOKROOMACCEPT)
    public List<RoomBook> getOrderDetailSaleTrue() {
        return orderDetailService.getOrderDetailSaleTrue();
    }

    //lấy ra order history
    @GetMapping(PathResources.LISTBOOKROOMHISTORY)
    public List<RoomBook> listRoomBookHistory() {
        return orderDetailService.getListRoomBookHistory();
    }

    //lấy ra order admin
    @GetMapping(PathResources.LISTBOOKROOMADMIN)
    public List<RoomBook> getOrderAdmin() {
        return orderDetailService.getOrderAdmin();
    }

    // lấy ra danh sách các phòng khách hàng đã đặt theo từng khách hàng
    @GetMapping(PathResources.LISTBOOKROOMCUSTOMER)
    public List<RoomBookCustomer> listRoomBookCustomer(@Param("idCustomer") int idCustomer) {
        return orderDetailService.getListRoomBookCustomer(idCustomer);
    }

    // lấy ra danh sách lich su order by id customer
    @GetMapping(PathResources.LISTBOOKROOMCUSTOMERHISTORY)
    public List<RoomBookCustomer> listRoomBookCustomerHistory(@Param("idCustomer") int idCustomer) {
        return orderDetailService.getListRoomBookCustomerHistory(idCustomer);
    }

    // tìm kiếm phòng theo idTypeRoom, idBranch, số chỗ ngồi
    @GetMapping(PathResources.SEARCHROOM)
    public List<SearchRoomSale> listRoomSaleSearch(@Param("idTypeRoom") int idTypeRoom,
                                                   @Param("idBranch") int idBranch,
                                                   @Param("soChoNgoi") int soChoNgoi,
                                                   @Param("startDate") Date startDate,
                                                   @Param("endDate") Date endDate) {
        return orderDetailService.listRoomSaleSearch(idTypeRoom, idBranch, soChoNgoi, startDate, endDate);
    }

    @GetMapping(PathResources.LISTROOMSALE)
    public List<CheckRoom> listRoomSale(@PathVariable("idTypeRoom") String idTypeRoom,
                                        @PathVariable("idBranch") String idBranch) {
        return orderDetailService.listRoomSale(idTypeRoom, idBranch);
    }

    // lấy ra chi tiết phòng đã đặt theo id orderdetail
    @GetMapping(PathResources.ROOKBOOKDETAILSALE)
    public RoomCustomer getRoomDetailSale(@Param("idOrderDetail") int idOrderDetail) {
        return orderDetailService.listRoomBook(idOrderDetail);
    }

    //sale accept
    @PostMapping(PathResources.SALEACCEPT)
    public MessageReponse saleAccept(@Param("idOrderDetail") int idOrderDetail,
                                     @RequestBody RoomBookAccecpSale roomBookAccecpSale) {
        return bookService.saleAccept(idOrderDetail,roomBookAccecpSale);
    }

    //sale accept update
    @PostMapping(PathResources.SALEACCEPTUPDATE)
    public MessageReponse saleAcceptUpdate(@Param("idOrderDetail") int idOrderDetail,
                                     @RequestBody RoomBookAccecpSale roomBookAccecpSale) {
        return bookService.saleAcceptUpdate(idOrderDetail,roomBookAccecpSale);
    }

    //sale not accept
    @PostMapping(PathResources.SALENOTACCEPT)
    public MessageReponse saleNotAccept(@Param("idOrderDetail") int idOrderDetail) {
        return bookService.saleNotAccept(idOrderDetail);
    }


    // lấy ra thông tin check in/out phòng của lễ tân
    @GetMapping(PathResources.ROOKBOOKLETAN)
    public List<RoomBookLeTan> getRoomLeTan(@Param("idBranch") int idBranch,
                                      @Param("datePresent") Date datePresent) {
        return bookService.listRoomLeTan(idBranch, datePresent);
    }

    // cập nhật khách vào ra
    @PostMapping(PathResources.CHECKINOUTLETAN)
    public MessageReponse updateCheckInOut(@Param("idScheduleDetail") int idScheduleDetail) {
        return bookService.updateCheckInOut(idScheduleDetail);
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
