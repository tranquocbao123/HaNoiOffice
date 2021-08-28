package com.example.cs_office.Service;

import com.example.cs_office.Model.InFoRoom.InFoRoomByStartEndTypeRomBranch;
import com.example.cs_office.Model.RoomBook.*;
import com.example.cs_office.Model.Entity.*;
import com.example.cs_office.Model.Search.SearchRoom;
import com.example.cs_office.Util.Message;
import com.example.cs_office.Util.MessageReponse;
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

    public MessageReponse acceptBookRoom(int idOrderDetail) {
        Optional<OrderDetail> orderDetail = orderDetailService.getOrderDetailById(idOrderDetail);
        MessageReponse messageReponse = new MessageReponse();
        int result = 0;
        if (orderDetail != null) {
            result = orderDetailService.updateOrderDetailByIdOrderDetail(idOrderDetail);
        }
       if(result != 0 ) {
           messageReponse.setMessage(Message.ACCEPTBOOKROOMSUCCESS);
        }else {
           messageReponse.setMessage(Message.ACCEPTBOOKROOMFAIL);
       }
       return messageReponse;
    }

    public MessageReponse cancelBookRoom(int idOrderDetail) {
        Optional<OrderDetail> orderDetail = orderDetailService.getOrderDetailById(idOrderDetail);
        MessageReponse messageReponse = new MessageReponse();
        int result = 0;
        if (orderDetail != null) {
            orderDetailService.deleteOrderDetail(idOrderDetail);
        }
        if(result != 0 ) {
            messageReponse.setMessage(Message.NOTACCEPTBOOKROOMSUCCESS);
        }else {
            messageReponse.setMessage(Message.NOTACCEPTBOOKROOMFAIL);
        }
        return messageReponse;
    }

    public int paySuccess(int idOrderDetail) {
        Optional<OrderDetail> orderDetail = orderDetailService.getOrderDetailById(idOrderDetail);
        MessageReponse messageReponse = new MessageReponse();
        int result = 0;
        if (orderDetail != null) {
            result = orderDetailService.updateStatusByIdOrderDetail(idOrderDetail);

        }
        return result;
    }

    public MessageReponse bookRoomLTNotAccept(RoomBookLT roomBookLT) {
        Optional<Room> room = roomService.getRoomById(Integer.parseInt(roomBookLT.getIdRoom()));
        Optional<Customer> customer = customerService.getById(roomBookLT.getIdCustomer());
        MessageReponse messageReponse = new MessageReponse();
        if (room.isPresent() && customer != null) {

            //insert orders
            Orders orders = new Orders();
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
                    scheduledetail.setDatePresent(Date.valueOf(next));
                    scheduledetail.setSchedule(schedule);
                    scheduledetail.setShift(shift.get());
                    scheduleDetailService.addNewScheduledetail(scheduledetail);
                }
            }
            if (roomBookLT.getListIdServiceSelected().size() > 0) {
                //insert service detail
                for (Integer j : roomBookLT.getListIdServiceSelected()) {
                    Optional<com.example.cs_office.Model.Entity.Service> service = serService.getServiceById(j);
                    ServiceDetail serviceDetail = new ServiceDetail();
                    serviceDetail.setSchedule(schedule);
                    serviceDetail.setService1(service.get());
                    serviceDetailService.addNewServiceDetail(serviceDetail);
                }
            }
            messageReponse.setMessage(Message.ORDERSUCCESS);
            return messageReponse;
        } else {
            messageReponse.setMessage(Message.ORDERNOTSUCCESS);
            return messageReponse;
        }
    }

    public MessageReponse bookRookKLTNotAccept(RoomBookKLT roomBookKLT) {
        Optional<Room> room = roomService.getRoomById(Integer.parseInt(roomBookKLT.getIdRoom()));
        Optional<Customer> customer = customerService.getById(roomBookKLT.getIdCustomer());
        MessageReponse messageReponse = new MessageReponse();
        if (room.isPresent() && customer != null) {

            //insert orders
            Orders orders = new Orders();
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
                schedule.setStartDate(obj.getStartDate());
                schedule.setEndDate(obj.getStartDate());
                schedule.setOrderDetail(orderDetail);
                scheduleService.addNewSchedule(schedule);

                //insert schedule detail
                for (Integer i : obj.getListShift()) {
                    Scheduledetail scheduledetail = new Scheduledetail();
                    scheduledetail.setDatePresent(obj.getStartDate());
                    Optional<Shift> shift = shiftService.getShiftById(i);
                    scheduledetail.setShift(shift.get());
                    scheduledetail.setSchedule(schedule);
                    scheduleDetailService.addNewScheduledetail(scheduledetail);
                }
                if (obj.getListService().size() > 0) {
                    //insert service detail
                    for (Integer j : obj.getListService()) {
                        ServiceDetail serviceDetail = new ServiceDetail();
                        serviceDetail.setSchedule(schedule);
                        Optional<com.example.cs_office.Model.Entity.Service> service = serService.getServiceById(j);
                        serviceDetail.setService1(service.get());
                        serviceDetailService.addNewServiceDetail(serviceDetail);
                    }
                }
            }
            messageReponse.setMessage(Message.ORDERSUCCESS);
            return messageReponse;
        } else {
            messageReponse.setMessage(Message.ORDERNOTSUCCESS);
            return messageReponse;
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
                                if (listScheduleDetail.size() > 0) {
                                    String s;
                                    for (Scheduledetail scheduledetail : listScheduleDetail) {
                                        Optional<Shift> shift = shiftService.getShiftById(scheduledetail.getShift().getId());
                                        if (shift.isPresent()) {
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

    public MessageReponse saleAccept(int idOrderDetail, RoomBookAccecpSale roomBookAccecpSale) {
        MessageReponse messageReponse = new MessageReponse();
        boolean result = deleteSchedule(idOrderDetail);
        if (result) {
            Optional<Room> room = roomService.getRoomById(Integer.parseInt(roomBookAccecpSale.getIdRoom()));
            Optional<Customer> customer = customerService.getById(roomBookAccecpSale.getIdCustomer());
            if (room.isPresent() && customer != null) {

                //insert orders
                Orders orders = new Orders();
                orders.setCustomer(customer.get());
                orderService.addNewOrder(orders);

                //insert orderDetail
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setAcceptance(true);
                orderDetail.setRoom(room.get());
                orderDetail.setOrders2(orders);
                orderDetailService.addNewOrderDetail(orderDetail);

                for (ScheduleSale obj : roomBookAccecpSale.getSchedules()) {

                    //insert schedule
                    Schedule schedule = new Schedule();
                    schedule.setStartDate(obj.getStartDate());
                    schedule.setEndDate(obj.getEndDate());
                    schedule.setOrderDetail(orderDetail);
                    scheduleService.addNewSchedule(schedule);

                    //insert schedule detail
                    String startDate = String.valueOf(obj.getStartDate());
                    String endDate = String.valueOf(obj.getEndDate());
                    LocalDate start = LocalDate.parse(startDate),
                            end = LocalDate.parse(endDate);
                    LocalDate next = start.minusDays(1);
                    while ((next = next.plusDays(1)).isBefore(end.plusDays(1))) {
                        for (Integer i : obj.getListShift()) {
                            Scheduledetail scheduledetail = new Scheduledetail();
                            Optional<Shift> shift = shiftService.getShiftById(i);
                            scheduledetail.setDatePresent(Date.valueOf(next));
                            scheduledetail.setSchedule(schedule);
                            scheduledetail.setShift(shift.get());
                            scheduleDetailService.addNewScheduledetail(scheduledetail);
                        }
                    }
                    if (obj.getListService().size() > 0) {
                        //insert service detail
                        for (Integer j : obj.getListService()) {
                            ServiceDetail serviceDetail = new ServiceDetail();
                            serviceDetail.setSchedule(schedule);
                            Optional<com.example.cs_office.Model.Entity.Service> service = serService.getServiceById(j);
                            serviceDetail.setService1(service.get());
                            serviceDetailService.addNewServiceDetail(serviceDetail);
                        }
                    }
                }
                messageReponse.setMessage(Message.ACCEPTSUCCESS);
            }
        } else {
            messageReponse.setMessage(Message.ACCEPTFAIl);
        }
        return messageReponse;
    }

    public MessageReponse saleNotAccept(int idOrderDetail) {
        MessageReponse messageReponse = new MessageReponse();
        boolean result = deleteSchedule(idOrderDetail);
        if (result) {
            messageReponse.setMessage(Message.NOTACCEPTSUCCESS);
        } else {
            messageReponse.setMessage(Message.NOTACCEPTFAIl);
        }
        return messageReponse;
    }

    public boolean deleteSchedule(int idOrderDetail) {
        Optional<OrderDetail> orderDetail = orderDetailService.getOrderDetailById(idOrderDetail);
        if (orderDetail.isPresent()) {
            List<Schedule> listSchedule = scheduleService.getListScheduleByIdOrderDetail2(idOrderDetail);
            if (listSchedule.size() > 0) {
                for (Schedule schedule : listSchedule) {
                    scheduleDetailService.deleteScheduledetailByIdSchedule(schedule.getId());
                    serviceDetailService.deleteServiceDetailByIdSchedule(schedule.getId());
                    scheduleService.deleteSchedule(schedule.getId());
                }
            } else {
                return false;
            }
            orderDetailService.deleteOrderDetail(idOrderDetail);
            orderService.deleteOrder(orderDetail.get().getOrders2().getId());
        } else {
            return false;
        }
        return true;
    }

    public List<RoomBookLeTan> listRoomLeTan(int idBranch, Date datePresent) {
        List<RoomBookLeTan> listDataTrue = new ArrayList<>();
        List<RoomBookLeTan> listData = new ArrayList<>();
        List<Scheduledetail> listScheduleDetail = scheduleDetailService.getScheduledetailByDate(datePresent);
        if (listScheduleDetail.size() == 0) {
            return listDataTrue;
        } else {
            for (Scheduledetail scheduledetail : listScheduleDetail) {
                RoomBookLeTan roomBookLeTan = new RoomBookLeTan();
                roomBookLeTan.setIdScheduleDetail(scheduledetail.getId());
                roomBookLeTan.setShift(scheduledetail.getShift().getStartTime() + " - " + scheduledetail.getShift().getEndTime());
                roomBookLeTan.setStatus(scheduledetail.isCheckinout());
                List<ServiceDetail> serviceDetailServices = serviceDetailService.getServiceDetailByIdSchedule2(scheduledetail.getSchedule().getId());
                if (serviceDetailServices.size() == 0) {
                    roomBookLeTan.setListService(null);
                } else {
                    List<String> listService = new ArrayList<>();
                    for (ServiceDetail serviceDetail : serviceDetailServices) {
                        Optional<com.example.cs_office.Model.Entity.Service> service = serService.getServiceById(serviceDetail.getService1().getId());
                        listService.add(service.get().getName());
                    }
                    roomBookLeTan.setListService(listService);
                }
                Optional<Schedule> schedule = scheduleService.getScheduleById(scheduledetail.getSchedule().getId());
                OrderDetail orderDetail = schedule.get().getOrderDetail();
                Room room = orderDetail.getRoom();
                roomBookLeTan.setNameRoom(room.getName());
                Orders orders = orderDetail.getOrders2();
                roomBookLeTan.setIdCustomer(orders.getCustomer().getId());
                roomBookLeTan.setNameCustomer(orders.getCustomer().getFirstName() + " " + orders.getCustomer().getLastName());
                roomBookLeTan.setIdBranch(room.getBranch1().getId());
                listData.add(roomBookLeTan);
            }
            for (RoomBookLeTan roomBookLeTan : listData) {
                if (roomBookLeTan.getIdBranch() == idBranch) {
                    listDataTrue.add(roomBookLeTan);
                }
            }
            return listDataTrue;
        }
    }

    public MessageReponse updateCheckInOut(int idScheduleDetail) {
        MessageReponse messageReponse = new MessageReponse();
        int result = scheduleDetailService.updateScheduleById(idScheduleDetail);
        if (result > 0) {
            messageReponse.setMessage(Message.CHECKINOUTSUCCESS);
        } else {
            messageReponse.setMessage(Message.CHECKINOUTFAIL);
        }
        return messageReponse;
    }
}
