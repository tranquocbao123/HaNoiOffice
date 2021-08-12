package com.example.cs_office.Service;

import com.example.cs_office.Model.InFoRoom.InFoRoomByStartEndTypeRomBranch;
import com.example.cs_office.Model.RoomBook.RoomBookLT;
import com.example.cs_office.Model.Entity.*;
import com.example.cs_office.Model.RoomBook.RoomBookKLT;
import com.example.cs_office.Model.RoomBook.ScheduleKLT;
import com.example.cs_office.Model.Search.SearchRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private OrderService orderService;

    private OrderDetailService orderDetailService;

    private ServiceDetailService serviceDetailService;

    private ScheduleService scheduleService;

    private ScheduleDetailService scheduleDetailService;

    private CustomerService customerService;

    private RoomService roomService;

    private ShiftService shiftService;

    private SerService serService;

    @Autowired
    public BookService(OrderService orderService, OrderDetailService orderDetailService, ServiceDetailService serviceDetailService, ScheduleService scheduleService, ScheduleDetailService scheduleDetailService, CustomerService customerService, RoomService roomService, ShiftService shiftService, SerService serService) {
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
        this.serviceDetailService = serviceDetailService;
        this.scheduleService = scheduleService;
        this.scheduleDetailService = scheduleDetailService;
        this.customerService = customerService;
        this.roomService = roomService;
        this.shiftService = shiftService;
        this.serService = serService;
    }

    public int acceptBookRoom(int idOrderDetail) {
        Optional<OrderDetail> orderDetail = orderDetailService.getOrderDetailById(idOrderDetail);
        int result = 0;
        if (orderDetail != null) {
            orderService.updateOrderByIdOrderDetail(orderDetail.get().getOrders2().getId());
            orderDetailService.updateOrderDetailByIdOrderDetail(idOrderDetail);
            List<Schedule> listSchedule = scheduleService.getListScheduleByIdOrderDetail(idOrderDetail);
            for (Schedule obj : listSchedule) {
                scheduleService.updateScheduleByIdOrderDetail(idOrderDetail);
                List<Scheduledetail> listScheduleDetail = scheduleDetailService.getListScheduleDetailByIdSchedule(obj.getId());
                for (Scheduledetail scheduledetail : listScheduleDetail) {
                    scheduleDetailService.updateScheduleByIdSchedule(scheduledetail.getId());
                }
                List<ServiceDetail> listServiceDetail = serviceDetailService.getServiceDetailByIdSchedule(obj.getId());
                for (ServiceDetail serviceDetail : listServiceDetail) {
                    result = serviceDetailService.updateServiceDetailByIdSchedule(serviceDetail.getId());
                }
            }
        }
        return result;
    }

    public int cancelBookRoom(int idOrderDetail) {
        Optional<OrderDetail> orderDetail = orderDetailService.getOrderDetailById(idOrderDetail);
        int result = 0;
        if (orderDetail != null) {
            List<Schedule> listSchedule = scheduleService.getListScheduleByIdOrderDetail(idOrderDetail);
            for (Schedule schedule : listSchedule) {
                serviceDetailService.deleteServiceDetailByIdSchedule(schedule.getId());
                scheduleDetailService.deleteScheduledetailByIdSchedule(schedule.getId());
            }
            scheduleService.deleteScheduleByIdOrderDetail(idOrderDetail);
            orderDetailService.deleteOrderDetail(idOrderDetail);
            orderService.deleteOrder(orderDetail.get().getOrders2().getId());
        }
        return result;
    }

    public int paySuccess(int idOrderDetail) {
        Optional<OrderDetail> orderDetail = orderDetailService.getOrderDetailById(idOrderDetail);
        int result = 0;
        if (orderDetail != null) {
            orderService.updateStatusByIdOrderDetail(orderDetail.get().getOrders2().getId());
            orderDetailService.updateStatusByIdOrderDetail(idOrderDetail);
            scheduleService.updateStatusByIdOrderDetail(idOrderDetail);
            result = 1;
        }
        return result;
    }

    public boolean bookRoomLTNotAccept(RoomBookLT roomBookLT) {
        Optional<Room> room = roomService.getRoomById(Integer.parseInt(roomBookLT.getIdRoom()));
        Optional<Customer> customer = customerService.getById(roomBookLT.getIdCustomer());
        if (room.isPresent() && customer != null) {

            //insert orders
            Orders orders = new Orders();
            orders.setAcceptance(false);
            orders.setCustomer(customer.get());
            orderService.addNewOrder(orders);

            //insert orderDetail
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setAcceptance(false);
            orderDetail.setRoom(room.get());
            orderDetail.setOrders2(orders);
            orderDetailService.addNewOrderDetail(orderDetail);

            //insert schedule
            Schedule schedule = new Schedule();
            schedule.setAcceptance(false);
            schedule.setEndDate(roomBookLT.getEndDate());
            schedule.setStartDate(roomBookLT.getStartDate());
            schedule.setOrderDetail(orderDetail);
            scheduleService.addNewSchedule(schedule);

            //insert schedule detail
            String startDate = String.valueOf(roomBookLT.getStartDate());
            String endDate = String.valueOf(roomBookLT.getEndDate());
            LocalDate start = LocalDate.parse(startDate),
                    end = LocalDate.parse(endDate);
            LocalDate next = start.minusDays(1);
            while ((next = next.plusDays(1)).isBefore(end.plusDays(1))) {
                for (Integer i : roomBookLT.getListIdScheduleDetail()) {
                    Scheduledetail scheduledetail = new Scheduledetail();
                    Optional<Shift> shift = shiftService.getShiftById(i);
                    scheduledetail.setAcceptance(false);
                    scheduledetail.setDatePresent(Date.valueOf(next));
                    scheduledetail.setSchedule(schedule);
                    scheduledetail.setShift(shift.get());
                    scheduleDetailService.addNewScheduledetail(scheduledetail);
                }
            }

            //insert service detail
            for (Integer j : roomBookLT.getListIdServiceSelected()) {
                Optional<com.example.cs_office.Model.Entity.Service> service = serService.getServiceById(j);
                ServiceDetail serviceDetail = new ServiceDetail();
                serviceDetail.setAcceptance(false);
                serviceDetail.setSchedule(schedule);
                serviceDetail.setService1(service.get());
                serviceDetailService.addNewServiceDetail(serviceDetail);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean bookRookKLTNotAccept(RoomBookKLT roomBookKLT) {
        Optional<Room> room = roomService.getRoomById(Integer.parseInt(roomBookKLT.getIdRoom()));
        Optional<Customer> customer = customerService.getById(roomBookKLT.getIdCustomer());
        if (room.isPresent() && customer != null ) {

            //insert orders
            Orders orders = new Orders();
            orders.setAcceptance(false);
            orders.setCustomer(customer.get());
            orderService.addNewOrder(orders);

            //insert orderDetail
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setAcceptance(false);
            orderDetail.setRoom(room.get());
            orderDetail.setOrders2(orders);
            orderDetailService.addNewOrderDetail(orderDetail);
            for (ScheduleKLT obj : roomBookKLT.getSchedules()) {

                //insert schedule
                Schedule schedule = new Schedule();
                schedule.setAcceptance(false);
                schedule.setStartDate(obj.getStartDate());
                schedule.setEndDate(obj.getStartDate());
                schedule.setOrderDetail(orderDetail);
                scheduleService.addNewSchedule(schedule);

                //insert schedule detail
                for (Integer i : obj.getListShift()) {
                    Scheduledetail scheduledetail = new Scheduledetail();
                    scheduledetail.setAcceptance(false);
                    scheduledetail.setDatePresent(obj.getStartDate());
                    Optional<Shift> shift = shiftService.getShiftById(i);
                    scheduledetail.setShift(shift.get());
                    scheduledetail.setSchedule(schedule);
                    scheduleDetailService.addNewScheduledetail(scheduledetail);
                }

                //insert service detail
                for (Integer j : obj.getListService()) {
                    ServiceDetail serviceDetail = new ServiceDetail();
                    serviceDetail.setAcceptance(false);
                    serviceDetail.setSchedule(schedule);
                    Optional<com.example.cs_office.Model.Entity.Service> service = serService.getServiceById(j);
                    serviceDetail.setService1(service.get());
                    serviceDetailService.addNewServiceDetail(serviceDetail);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public List<InFoRoomByStartEndTypeRomBranch> getListInfoRoom(SearchRoom searchRoom) {
        List<Room> listRoom = roomService.getListRoomByTypeRoomAndBranch(searchRoom.getIdTypeRoom(), searchRoom.getIdBranch());
        List<InFoRoomByStartEndTypeRomBranch> list = new ArrayList<>();
        if (listRoom.size() > 0) {
            for (Room room : listRoom) {
                List<OrderDetail> listOrderDetail = orderDetailService.listOrderDetailByIdRoom(room.getId());
                if (listOrderDetail.size() > 0) {
                    for (OrderDetail orderDetail : listOrderDetail) {
                        List<Schedule> listSchedule = scheduleService.listScheduleByEndStart(searchRoom.getEndDate(), searchRoom.getStartDate(), orderDetail.getId());
                        if (listSchedule.size() > 0) {
                            for (Schedule schedule : listSchedule) {
                                InFoRoomByStartEndTypeRomBranch obj = new InFoRoomByStartEndTypeRomBranch();
                                obj.setIdRoom(room.getId());
                                obj.setTypeRoom(room.getBranch1().getName());
                                obj.setBranch(room.getTypeRoom().getName());
                                obj.setDate(schedule.getStartDate() + "--->" + schedule.getEndDate());
                                List<String> listService = new ArrayList<>();
                                List<String> listShift = new ArrayList<>();
                                List<ServiceDetail> listServiceDetail = serviceDetailService.getServiceDetailByIdSchedule1(schedule.getId());
                                if (listServiceDetail.size() > 0) {
                                    String sv;
                                    for (ServiceDetail serviceDetail : listServiceDetail) {
                                        Optional<com.example.cs_office.Model.Entity.Service> service = serService.getServiceById(serviceDetail.getService1().getId());
                                        if (service.isPresent()) {
                                            sv = service.get().getName();
                                            listService.add(sv);
                                        }
                                    }
                                }
                                List<Scheduledetail> listScheduleDetail = scheduleDetailService.getListScheduleDetailByIdSchedule1(schedule.getId(), schedule.getStartDate());
                                if(listScheduleDetail.size() > 0) {
                                    String s;
                                    for (Scheduledetail scheduledetail : listScheduleDetail) {
                                        Optional<Shift> shift = shiftService.getShiftById(scheduledetail.getShift().getId());
                                        if(shift.isPresent()) {
                                            s = shift.get().getStartTime() + "-" + shift.get().getEndTime();
                                            listShift.add(s);
                                        }
                                    }
                                }
                                String service = null;
                                for (String j : listService) {
                                    service += ", " + j;
                                }
                                if (service != null) {
                                    obj.setService(service.substring(6, service.length()));
                                }
                                String shift = null;
                                for (String j : listShift) {
                                    shift += ", " + j;
                                }
                                if (shift != null) {
                                    obj.setTime(shift.substring(6, shift.length()));
                                }
                                list.add(obj);
                            }
                        }
                    }
                } else {
                    InFoRoomByStartEndTypeRomBranch info = new InFoRoomByStartEndTypeRomBranch();
                    info.setIdRoom(room.getId());
                    info.setTypeRoom(room.getTypeRoom().getName());
                    info.setBranch(room.getBranch1().getName());
                    info.setService("");
                    info.setDate("");
                    info.setTime("");
                    list.add(info);
                }
            }
        } else {
            return list;
        }
        return list;
    }
}
