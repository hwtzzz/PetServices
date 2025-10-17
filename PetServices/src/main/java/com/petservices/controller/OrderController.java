package com.petservices.controller;


import com.petservices.dto.OrderListDto;
import com.petservices.dto.PageDto;
import com.petservices.entity.Goods;
import com.petservices.entity.Order;
import com.petservices.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2025-02-23
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;


    /**
     * 获取所有订单分页
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/getOrderList/{currentPage}/{pageSize}")
    public PageDto getOrderList(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        return orderService.getOrderList(currentPage, pageSize);
    }

    /**
     * 修改订单信息
     *
     * @param orderId
     * @param flag
     */
    @GetMapping("/updateOrderFlag/{orderId}/{flag}")
    public PageDto updateOrder(@PathVariable("orderId") Integer orderId , @PathVariable("flag") Integer flag) {
        return orderService.updateOrder(orderId , flag);
    }

    /**
     * 添加订单
     *
     * @param order
     * @return
     */
    @PostMapping("/addOrder")
    public PageDto addOrder(@RequestBody Order order) {
        return orderService.addOrder(order);
    }

    /**
     * 删除订单信息
     *
     * @param orderId
     * @return
     */
    @GetMapping("/deleteOrder/{orderId}")
    public PageDto deleteOrder(@PathVariable Integer orderId) {
        return orderService.deleteOrder(orderId);
    }

    /**
     * 查找订单
     *
     * @param flag
     * @return
     */
    @GetMapping("/searchOrder/{flag}")
    public List<OrderListDto> searchOrder(@PathVariable Integer flag) {
        return orderService.searchOrder(flag);
    }

    //前台
    @PostMapping("/userAddOrder")
    public List<Goods> userAddOrder(@RequestBody Order order) {
        return orderService.userAddOrder(order);
    }

    @GetMapping("/getMyOrder/{userId}")
    public List<OrderListDto> getMyOrder(@PathVariable Integer userId) {
        return orderService.getMyOrder(userId);
    }

    @GetMapping("/userUpdateOrderFlag/{orderId}/{flag}")
    public List<OrderListDto> userUpdateOrderFlag(@PathVariable("orderId") Integer orderId , @PathVariable("flag") Integer flag) {
        return orderService.userUpdateOrderFlag(orderId , flag);
    }

}

