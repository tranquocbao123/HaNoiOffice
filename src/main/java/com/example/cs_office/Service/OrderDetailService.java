package com.example.cs_office.Service;


import com.example.cs_office.Model.Dto.CheckRoom;
import com.example.cs_office.Model.Dto.RoomBook;
import com.example.cs_office.Model.Dto.RoomCustomer;
import com.example.cs_office.Model.Entity.*;
import com.example.cs_office.Repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    private final ScheduleRepository scheduleRepository;

    private final ScheduleDetailRepository scheduleDetailRepository;

    private final RoomRepository roomRepository;

    private final OrderRepository orderRepository;

    private final ServiceDetailRepository serviceDetailRepository;

    private final ServiceRepository serviceRepository;

    private final TypeRoomRepository typeRoomRepository;

    public OrderDetailService(OrderDetailRepository orderDetailRepository, ScheduleRepository scheduleRepository, ScheduleDetailRepository scheduleDetailRepository, RoomRepository roomRepository, OrderRepository orderRepository, ServiceDetailRepository serviceDetailRepository, ServiceRepository serviceRepository, TypeRoomRepository typeRoomRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.scheduleRepository = scheduleRepository;
        this.scheduleDetailRepository = scheduleDetailRepository;
        this.roomRepository = roomRepository;
        this.orderRepository = orderRepository;
        this.serviceDetailRepository = serviceDetailRepository;
        this.serviceRepository = serviceRepository;
        this.typeRoomRepository = typeRoomRepository;
    }

    public List<OrderDetail> getOrderDetail() {
        return orderDetailRepository.findAll();
    }

    public List<OrderDetail> getOrderDetailByStatus(boolean status) {

        return orderDetailRepository.findOrderDetailByStatus(status);
    }

    public Optional<OrderDetail> getOrderDetailById(int orderdetailId) {
        Optional<OrderDetail> orderdetail = orderDetailRepository.findById(orderdetailId);
        return orderdetail;
    }

    public void addNewOrderDetail(OrderDetail orderDetail) {
        Optional<OrderDetail> orderDetailOptional =
                orderDetailRepository.findOrderDetailById(orderDetail.getId());
        if (orderDetailOptional.isPresent()) {
            throw new IllegalStateException("Id taken");
        }
        orderDetailRepository.save(orderDetail);
    }

    public void deleteOrderDetail(int orderDetailId) {
        boolean exists = orderDetailRepository.existsById(orderDetailId);
        if (!exists) {
            throw new IllegalStateException("order detail with id " + orderDetailId + " does not exists");
        }
        orderDetailRepository.deleteById(orderDetailId);
    }

    @Transactional
    public OrderDetail updateOrderDetailStatus(OrderDetail orderDetail) {
        orderDetail.setStatus(false);
        return orderDetailRepository.save(orderDetail);
    }

    @Transactional
    public OrderDetail updateOrderDetailBlack(OrderDetail orderDetail) {
        orderDetail.setStatus(true);
        return orderDetailRepository.save(orderDetail);
    }

    @Transactional
    public OrderDetail updateOrderDetail(OrderDetail orderDetail, int orderDetailId) {
        OrderDetail orderDetail1 = this.orderDetailRepository.getOne(orderDetailId);
        BeanUtils.copyProperties(orderDetail, orderDetail1);
        return orderDetailRepository.saveAndFlush(orderDetail1);
    }

    public int countOrderDetail() {
        return orderDetailRepository.getCountOrderDetail();
    }

    public CheckRoom getListOrderDetailByIdRoom(int idRoom) {
        CheckRoom checkRoom = new CheckRoom();
        return checkRoom;
    }

    public List<RoomBook> getListRoomBook() {
        List<RoomBook> listRoomBook = new ArrayList<>();
        /*List<Orders> listOrders = orderRepository.findOrderByStatusAndAcceptance();
        if (listOrders.size() > 0) {
            for (int i = 0; i < listOrders.size(); i++) {
                List<String> listTime = new ArrayList<>();
                List<String> listService = new ArrayList<>();
                List<Integer> listIdOrderDetail = new ArrayList<>();
                RoomBook roomBook = new RoomBook();
                roomBook.setIdCustomer(listOrders.get(i).getCustomer().getId());
                List<OrderDetail> listOrderDetail = orderDetailRepository.findOrderDetailByStatusAndAcceptanceAndIdOrder(listOrders.get(i).getId());
                for (OrderDetail orderDetail : listOrderDetail) {
                    Room room = roomRepository.findRoomById(orderDetail.getRoom().getId());
                    roomBook.setIdRoom(room.getId());
                    roomBook.setNameRoom(room.getName());
                    roomBook.setNameTypeRoom(room.getTypeRoom().getName());
                    roomBook.setNameBranch(room.getBranch1().getName());
                    roomBook.setIdOrderDetail(orderDetail.getId());
                    listIdOrderDetail.add(orderDetail.getId());
                    List<Schedule> listSchedule = scheduleRepository.getListScheduleByIdOrderDetailFalse(orderDetail.getId());
                    for (Schedule schedule : listSchedule) {
                        List<String> listDate = new ArrayList<>();
                        String date = schedule.getStartDate() + "--->" + schedule.getEndDate();
                        listDate.add(date);
                        Optional<Scheduledetail> scheduledetail = scheduleDetailRepository.findById(schedule.getScheduledetail().getId());
                        String time = scheduledetail.get().getStartTime() + "-" + scheduledetail.get().getEndTime();
                        listTime.add(time);
                        roomBook.setDate(listDate);
                    }
                    List<ServiceDetail> listServiceDetail = serviceDetailRepository.getListServiceDetailByIdOrderDetailFalse(orderDetail.getId());
                    for (ServiceDetail serviceDetail : listServiceDetail) {
                        Optional<com.example.cs_office.Model.Entity.Service> service = serviceRepository.findById(serviceDetail.getService1().getId());
                        String sv = service.get().getName();
                        listService.add(sv);
                    }
                }
                String time = null;
                for (String j : listTime) {
                    time += ", " + j;
                }
                String service = null;
                for (String j : listService) {
                    service += ", " + j;
                }
                roomBook.setTime(time.substring(6, time.length()));
                roomBook.setService(service.substring(6, service.length()));
                listRoomBook.add(roomBook);
            }
        }*/
        return listRoomBook;
    }

    @Transactional
    public int updateOrderDetailByIdOrderDetail(int idOrderDetail) {
        return orderDetailRepository.updateOrderDetailByIdOrderDetail(idOrderDetail);
    }

    @Transactional
    public int updateStatusByIdOrderDetail(int idOrderDetail) {
        return orderDetailRepository.updateStatusByIdOrderDetail(idOrderDetail);
    }

    public List<CheckRoom> listRoomSale(String idTypeRoom, String idBranch) {
        List<CheckRoom> listCheckRoom = new ArrayList<>();
        List<Room> listRoom = roomRepository.getListRoomByTypeRoomAndBranch(idTypeRoom, idBranch);
        if (listRoom.size() > 0) {
            for (Room room : listRoom) {
                listCheckRoom.add(getListOrderDetailByIdRoom(room.getId()));
            }
        }
        return listCheckRoom;
    }

    public List<RoomCustomer> listRoomBook(int idCustomer) {
        List<RoomCustomer> listRoomCustomer = new ArrayList<>();
        /*List<Orders> listOrders = orderRepository.listOrderDetailByIdCustomer(idCustomer);
        if (listOrders.size() > 0) {
            for (Orders orders : listOrders) {
                List<String> listTime = new ArrayList<>();
                List<String> listService = new ArrayList<>();
                double total = 0;
                double priceRoom = 0;
                double priceService = 0;
                double countCa = 0 ;
                double countDate = 0;
                RoomCustomer roomCustomer = new RoomCustomer();
                List<OrderDetail> listOrderDetail = orderDetailRepository.findOrderDetailByCustomer(orders.getId());
                for(OrderDetail orderDetail : listOrderDetail) {
                    roomCustomer.setIdOrderDetail(orderDetail.getId());
                    roomCustomer.setIdRoom(orderDetail.getRoom().getId());
                    Room room = roomRepository.findRoomById(orderDetail.getRoom().getId());
                    priceRoom = typeRoomRepository.findTypeRoomById(room.getTypeRoom().getId()).get().getPriceTypeRoom();
                    roomCustomer.setNameRoom(room.getName());
                    roomCustomer.setNameTypeRoom(room.getTypeRoom().getName());
                    roomCustomer.setNameBranch(room.getBranch1().getName());
                    List<ServiceDetail> listServiceDetail = serviceDetailRepository.getListServiceDetailByIdOrderDetailCustomer(orderDetail.getId());
                    for(ServiceDetail serviceDetail : listServiceDetail) {
                        Optional<com.example.cs_office.Model.Entity.Service> service = serviceRepository.findById(serviceDetail.getService1().getId());
                        String sv = service.get().getName();
                        listService.add(sv);
                        priceService += service.get().getPriceService();
                    }
                    List<Schedule> listSchedule = scheduleRepository.getListScheduleByIdOrderDetailCustomer(orderDetail.getId());
                    countCa = listSchedule.size();
                    for (Schedule schedule : listSchedule) {
                        List<String> listDate = new ArrayList<>();
                        String date = schedule.getStartDate() + "--->" + schedule.getEndDate();
                        listDate.add(date);
                        Optional<Scheduledetail> scheduledetail = scheduleDetailRepository.findById(schedule.getScheduledetail().getId());
                        String time = scheduledetail.get().getStartTime() + "-" + scheduledetail.get().getEndTime();
                        listTime.add(time);
                        roomCustomer.setDate(listDate);

                        countDate = schedule.getEndDate().getTime() - schedule.getStartDate().getTime();
                    }
                    String time = null;
                    for (String j : listTime) {
                        time += ", " + j;
                    }
                    String service = null;
                    for (String j : listService) {
                        service += ", " + j;
                    }
                    int date = (int) (countDate / (1000 * 60 * 60 * 24));
                    roomCustomer.setTotal((priceRoom+priceService)*date*countCa);
                    roomCustomer.setTime(time.substring(6, time.length()));
                    roomCustomer.setService(service.substring(6, service.length()));
                    roomCustomer.setStatus(orderDetail.isStatus());
                    listRoomCustomer.add(roomCustomer);
                }
            }
        }*/
        return listRoomCustomer;
    }

    public List<OrderDetail> listOrderDetailByIdRoom(int idRoom) {
        return orderDetailRepository.listOrderDetailByIdRoom(idRoom);
    }

    public double getTotalByIdOrderDetail(int idOrderDetail) {
        if (orderDetailRepository.getTotalByIdOrderDetail(idOrderDetail) == null) {
            return 0;
        } else {
            return Double.parseDouble(orderDetailRepository.getTotalByIdOrderDetail(idOrderDetail));
        }
    }

    public double getTotal() {
        if (orderDetailRepository.getTotal() == null) {
            return 0;
        } else {
            return Double.parseDouble(orderDetailRepository.getTotal());
        }
    }
}
