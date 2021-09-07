package com.example.cs_office.Service;


import com.example.cs_office.Model.Dto.*;
import com.example.cs_office.Model.Entity.*;
import com.example.cs_office.Model.Entity.Room;
import com.example.cs_office.Model.Search.ScheduleSale;
import com.example.cs_office.Model.Search.SearchRoomSale;
import com.example.cs_office.Repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

    public List<OrderDetail> getListOrderDetail() {
        return orderDetailRepository.getListOrderDetail();
    }

    public List<OrderDetail> getOrderDetailOrderByCreateDate() {
        return orderDetailRepository.getOrderDetailOrderByCreateDate();
    }

    public List<OrderDetail> getOrderDetailSale() {
        return orderDetailRepository.getOrderDetailSale();
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
        List<OrderDetail> listOrderDetail = getOrderDetailSale();
        if (listOrderDetail.size() == 0) {
            return null;
        } else {
            for (OrderDetail orderDetail : listOrderDetail) {
                RoomBook roomBook = new RoomBook();
                Optional<Orders> orders = orderRepository.findOrderById(orderDetail.getOrders2().getId());
                if (orders.isPresent()) {
                    roomBook.setIdCustomer(orders.get().getCustomer().getId());
                    roomBook.setNameCustomer(orders.get().getCustomer().getFirstName() + orders.get().getCustomer().getLastName());
                    roomBook.setNumberPhone(orders.get().getCustomer().getPhoneNumber());
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

    public List<RoomBook> getOrderDetailSaleTrue() {
        List<RoomBook> listRoomBook = new ArrayList<>();
        List<OrderDetail> listOrderDetail = orderDetailRepository.getOrderDetailSaleTrue();
        if (listOrderDetail.size() == 0) {
            return null;
        } else {
            for (OrderDetail orderDetail : listOrderDetail) {
                RoomBook roomBook = new RoomBook();
                Optional<Orders> orders = orderRepository.findOrderById(orderDetail.getOrders2().getId());
                if (orders.isPresent()) {
                    roomBook.setIdCustomer(orders.get().getCustomer().getId());
                    roomBook.setNameCustomer(orders.get().getCustomer().getFirstName() + orders.get().getCustomer().getLastName());
                    roomBook.setNumberPhone(orders.get().getCustomer().getPhoneNumber());
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

    public List<RoomBook> getListRoomBookHistory() {
        List<RoomBook> listRoomBook = new ArrayList<>();
        List<OrderDetail> listOrderDetail = orderDetailRepository.getOrderDetailHistory();
        if (listOrderDetail.size() == 0) {
            return null;
        } else {
            for (OrderDetail orderDetail : listOrderDetail) {
                RoomBook roomBook = new RoomBook();
                Optional<Orders> orders = orderRepository.findOrderById(orderDetail.getOrders2().getId());
                if (orders.isPresent()) {
                    roomBook.setIdCustomer(orders.get().getCustomer().getId());
                    roomBook.setNameCustomer(orders.get().getCustomer().getFirstName() + orders.get().getCustomer().getLastName());
                    roomBook.setNumberPhone(orders.get().getCustomer().getPhoneNumber());
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

    public List<RoomBook> getOrderAdmin() {
        List<RoomBook> listRoomBook = new ArrayList<>();
        List<OrderDetail> listOrderDetail = orderDetailRepository.getOrderAdmin();
        if (listOrderDetail.size() == 0) {
            return null;
        } else {
            for (OrderDetail orderDetail : listOrderDetail) {
                RoomBook roomBook = new RoomBook();
                Optional<Orders> orders = orderRepository.findOrderById(orderDetail.getOrders2().getId());
                if (orders.isPresent()) {
                    roomBook.setIdCustomer(orders.get().getCustomer().getId());
                    roomBook.setNameCustomer(orders.get().getCustomer().getFirstName() + orders.get().getCustomer().getLastName());
                    roomBook.setNumberPhone(orders.get().getCustomer().getPhoneNumber());
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

    public List<RoomBookCustomer> getListRoomBookCustomer(int idCustomer) {
        List<RoomBookCustomer> listRoomBookCustomer = new ArrayList<>();
        List<Orders> listOrder = orderRepository.findOrderByIdCustomer(idCustomer);
        if (listOrder.size() == 0) {
            return null;
        } else {
            for (Orders orders : listOrder) {
                List<OrderDetail> listOrderDetail = orderDetailRepository.getListOrderDetailByIdOrder1(orders.getId());
                if (listOrderDetail.size() == 0) {
                    System.out.println("Null");
                } else {
                    for (OrderDetail orderDetail : listOrderDetail) {
                        RoomBookCustomer roomBook = new RoomBookCustomer();
                        roomBook.setIdOrderDetail(orderDetail.getId());
                        roomBook.setCreateDate(orderDetail.getCreateDate());
                        roomBook.setStatusOrder(orderDetail.isAcceptance());
                        roomBook.setStatusPay(orderDetail.isStatus());
                        listRoomBookCustomer.add(roomBook);
                    }
                }
            }
            return listRoomBookCustomer;
        }
    }

    public List<RoomBookCustomer> getListRoomBookCustomerHistory(int idCustomer) {
        List<RoomBookCustomer> listRoomBookCustomer = new ArrayList<>();
        List<Orders> listOrder = orderRepository.findOrderByIdCustomer(idCustomer);
        if (listOrder.size() == 0) {
            return null;
        } else {
            for (Orders orders : listOrder) {
                List<OrderDetail> listOrderDetail = orderDetailRepository.getListOrderDetailByIdOrder2(orders.getId());
                if (listOrderDetail.size() == 0) {
                    System.out.println("Null");
                } else {
                    for (OrderDetail orderDetail : listOrderDetail) {
                        RoomBookCustomer roomBook = new RoomBookCustomer();
                        roomBook.setIdOrderDetail(orderDetail.getId());
                        roomBook.setCreateDate(orderDetail.getCreateDate());
                        roomBook.setStatusOrder(orderDetail.isAcceptance());
                        roomBook.setStatusPay(orderDetail.isStatus());
                        listRoomBookCustomer.add(roomBook);
                    }
                }
            }
            return listRoomBookCustomer;
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

    @Transactional
    public int updateDone(int idOrderDetail) {
        return orderDetailRepository.updateDone(idOrderDetail);
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
                roomCustomer.setRoom(room);
                List<ScheduleCustomer> listScheduleCustomer = new ArrayList<>();
                List<Schedule> listSchedule = scheduleRepository.getScheduleByIdOrderDetail(orderDetail.get().getId());
                if (listSchedule.size() == 0) {
                    roomCustomer.setListScheduleCustomer(null);
                } else {
                    for (Schedule schedule : listSchedule) {
                        List<ServiceDetail> listServiceDetail = serviceDetailRepository.getServiceDetailByIdSchedule(schedule.getId());
                        List<com.example.cs_office.Model.Entity.Service> listService = new ArrayList<>();
                        for (ServiceDetail serviceDetail : listServiceDetail) {
                            listService.add(serviceDetail.getService1());
                        }
                        String startDate = String.valueOf(schedule.getStartDate());
                        String endDate = String.valueOf(schedule.getEndDate());
                        LocalDate start = LocalDate.parse(startDate),
                                end = LocalDate.parse(endDate);
                        LocalDate next = start.minusDays(1);
                        while ((next = next.plusDays(1)).isBefore(end.plusDays(1))) {
                            ScheduleCustomer scheduleCustomer = new ScheduleCustomer();
                            List<Shift> listShift = new ArrayList<>();
                            List<Scheduledetail> listScheduleDetail = scheduleDetailRepository.getScheduleByIdScheduleAndDatePresent(schedule.getId(), Date.valueOf(next));
                            if (listScheduleDetail.size() == 0) {
                                scheduleCustomer.setListShift(null);
                                scheduleCustomer.setDatePresent(Date.valueOf(next));
                                scheduleCustomer.setListService(listService);
                                listScheduleCustomer.add(scheduleCustomer);
                            } else {
                                for (Scheduledetail scheduledetail : listScheduleDetail) {
                                    listShift.add(scheduledetail.getShift());
                                }
                                scheduleCustomer.setListShift(listShift);
                                scheduleCustomer.setDatePresent(Date.valueOf(next));
                                scheduleCustomer.setListService(listService);
                                listScheduleCustomer.add(scheduleCustomer);
                            }
                        }
                    }
                }
                roomCustomer.setListScheduleCustomer(listScheduleCustomer);
                return roomCustomer;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public List<SearchRoomSale> listRoomSaleSearch(int idTypeRoom, int idBranch, int soChoNgoi, Date ngayBD, Date ngayKT) {
        List<SearchRoomSale> listSearchRoomSale = new ArrayList<>();
        int max = soChoNgoi + 3;
        List<Room> listRoom = roomRepository.getRoomBySo(idTypeRoom, idBranch, soChoNgoi, max);
        if (listRoom.size() > 0) {
            for (Room room : listRoom) {
                List<OrderDetail> listOrderDetail = orderDetailRepository.findOrderDetailByIdRoom1(room.getId());
                if (listOrderDetail.size() > 0) {
                    for (OrderDetail orderDetail : listOrderDetail) {
                        List<Schedule> listSchedule = scheduleRepository.getScheduleByIdOrderDetail(orderDetail.getId());
                        if (listSchedule.size() != 0) {
                            for (Schedule schedule : listSchedule) {
                                String startDate = String.valueOf(schedule.getStartDate());
                                String endDate = String.valueOf(schedule.getEndDate());
                                String startDate1 = String.valueOf(ngayBD);
                                String endDate1 = String.valueOf(ngayKT);
                                LocalDate start = LocalDate.parse(startDate),
                                        end = LocalDate.parse(endDate);
                                LocalDate start1 = LocalDate.parse(startDate1),
                                        end1 = LocalDate.parse(endDate1).plusDays(1);
                                LocalDate next = start.minusDays(1);
                                while ((next = next.plusDays(1)).isBefore(end.plusDays(1))) {
                                    SearchRoomSale searchRoomSale = new SearchRoomSale();
                                    Boolean intervalContainsToday = (!next.isBefore(start1)) && next.isBefore(end1);
                                    if (intervalContainsToday) {
                                        List<Shift> listShift = new ArrayList<>();
                                        List<Scheduledetail> listScheduleDetail = scheduleDetailRepository.getScheduleByIdScheduleAndDatePresent(schedule.getId(), Date.valueOf(next));
                                        if (listScheduleDetail.size() == 0) {
                                            searchRoomSale.setRoom(room);
                                            searchRoomSale.setListShift(null);
                                            searchRoomSale.setDatePresent(Date.valueOf(next));
                                            listSearchRoomSale.add(searchRoomSale);
                                        } else {
                                            for (Scheduledetail scheduledetail : listScheduleDetail) {
                                                listShift.add(scheduledetail.getShift());
                                            }
                                            searchRoomSale.setRoom(room);
                                            searchRoomSale.setListShift(listShift);
                                            searchRoomSale.setDatePresent(Date.valueOf(next));
                                            listSearchRoomSale.add(searchRoomSale);
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    SearchRoomSale searchRoomSale = new SearchRoomSale();
                    searchRoomSale.setRoom(room);
                    searchRoomSale.setListShift(null);
                    searchRoomSale.setDatePresent(null);
                    listSearchRoomSale.add(searchRoomSale);
                }
            }
            return listSearchRoomSale;
        } else {
            return listSearchRoomSale;
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
