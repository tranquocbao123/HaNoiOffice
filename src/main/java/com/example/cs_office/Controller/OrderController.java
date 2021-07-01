package com.example.cs_office.Controller;

import com.example.cs_office.Model.Orders;
import com.example.cs_office.Service.OrderService;
import com.example.cs_office.Util.PathResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(PathResources.ORDER)
@CrossOrigin("*")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //list order
    @GetMapping(PathResources.FIND_ALL)
    public List<Orders> getOrder() {
        return orderService.getOrder();
    }

    //list order by status == fasle
    @GetMapping(PathResources.FIND_BY_STATUSFALSE)
    public List<Orders> getOrderByStatusFalse() {
        return orderService.getOrderByStatus(false);
    }

    //list order by status == true
    @GetMapping(PathResources.FIND_BY_STATUSTRUE)
    public List<Orders> getOrderByStatusTrue() {
        return orderService.getOrderByStatus(true);
    }

    // insert order
    @PostMapping(PathResources.SAVE)
    public void insertOrder(@RequestBody Orders orders) {
        orderService.addNewOrder(orders);
    }

    //search order by id
    @GetMapping(path = PathResources.FIND_BY_ID)
    public Optional<Orders> getOrderById(
            @PathVariable("id") int orderId) {
        return orderService.getOrderById(orderId);
    }

    //delete order by id
    @DeleteMapping(path = PathResources.DELETEBYID)
    public void deleteOrder(@PathVariable("id") int orderId) {
        orderService.deleteOrder(orderId);
    }

    //update order by status
    @PutMapping(PathResources.UPDATESTATUSFALSE)
    public void updateOrderStatus(
            @RequestBody Orders orders
    ) {
        orderService.updateOrdersStatus(orders);
    }

    //update order black by status
    @PutMapping(path = PathResources.UPDATESTATUSTRUE)
    public void updateOrdersBlack(
            @RequestBody Orders orders
    ) {
        orderService.updateOrdersBlack(orders);
    }

    //update order by id
    @PutMapping(path = PathResources.UPDATEBYID)
    public Orders updateOrder
    (@RequestBody Orders orders,
     @PathVariable("id") int orderId) {
        return orderService.updateOrder(orders, orderId);
    }

}
