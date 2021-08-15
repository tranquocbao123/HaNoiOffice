package com.example.cs_office.Service;


import com.example.cs_office.Model.Dto.CheckRoom;
import com.example.cs_office.Model.Dto.RoomBook;
import com.example.cs_office.Model.Dto.RoomCustomer;
import com.example.cs_office.Model.Dto.ScheduleCustomer;
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
        List<OrderDetail> listOrderDetail = getOrderDetail();
        if (listOrderDetail.size() == 0) {
            return null;
        } else {
            for (OrderDetail orderDetail : listOrderDetail) {
                RoomBook roomBook = new RoomBook();
                Optional<Orders> orders = orderRepository.findOrderById(orderDetail.getOrders2().getId());
                if (orders.isPresent()) {
                    roomBook.setIdCustomer(orders.get().getCustomer().getId());
                    roomBook.setNameCustomer(orders.get().getCustomer().getFirstName() + orders.get().getCustomer().getLastName());
                } else {
                    return null;
                }
                roomBook.setIdOrderDetail(orderDetail.getId());
                roomBook.setCreateDate(orderDetail.getCreateDate());
                roomBook.setStatusOrder(orderDetail.isAcceptance());
                roomBook.setStatusPay(orderDetail.isStatus());
                listRoomBook.add(roomBook);
            }
            return listRoomBook;
        }
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

    public RoomCustomer listRoomBook(int idOrderDetail) {
        RoomCustomer roomCustomer = new RoomCustomer();
        Optional<OrderDetail> orderDetail = orderDetailRepository.findOrderDetailById(idOrderDetail);
        if (orderDetail.isPresent()) {
            Room room = roomRepository.findRoomById(orderDetail.get().getRoom().getId());
            if (room != null) {
                roomCustomer.setBranch(room.getBranch1());
                roomCustomer.setTypeRoom(room.getTypeRoom());
                List<Room> listRoom = roomRepository.getRoomBySo(room.getTypeRoom().getId(),room.getBranch1().getId(),room.getSoChoNgoi()-3, room.getSoChoNgoi() + 3);
                if(listRoom.size() == 0) {
                    return null;
                }else {
                    roomCustomer.setListRoom(listRoom);
                    List<ScheduleCustomer> listScheduleCustomer = new ArrayList<>();
                    List<Schedule> listSchedule = scheduleRepository.getScheduleByIdOrderDetail(orderDetail.get().getId());
                    if(listSchedule.size() == 0) {
                        roomCustomer.setListScheduleCustomer(null);
                    }else{
                        for (Schedule schedule : listSchedule) {
                            List<ServiceDetail> listServiceDetail = serviceDetailRepository.getServiceDetailByIdSchedule(schedule.getId());
                            List<com.example.cs_office.Model.Entity.Service> listService = new ArrayList<>();
                            for (ServiceDetail serviceDetail : listServiceDetail) {
                                listService.add(serviceDetail.getService1());
                            }
                            List<Scheduledetail> listScheduleDetail = scheduleDetailRepository.getScheduleByIdSchedule(schedule.getId());
                            for(Scheduledetail scheduledetail : listScheduleDetail) {
                                List<Shift> listShift = new ArrayList<>();
                                ScheduleCustomer scheduleCustomer = new ScheduleCustomer();
                                listShift.add(scheduledetail.getShift());
                                scheduleCustomer.setListShift(listShift);
                                scheduleCustomer.setDatePresent(scheduledetail.getDatePresent());
                                scheduleCustomer.setListService(listService);
                                listScheduleCustomer.add(scheduleCustomer);
                            }
                        }
                    }
                    roomCustomer.setListScheduleCustomer(listScheduleCustomer);
                    return roomCustomer;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
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
