package com.petservices.service;

import com.petservices.dto.OrderListDto;
import com.petservices.dto.OrderListDto;
import com.petservices.dto.PageDto;
import com.petservices.entity.Goods;
import com.petservices.entity.Order;
import com.petservices.mapper.GoodsMapper;
import com.petservices.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ${author}
 * @since 2025-02-23
 */
@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    GoodsMapper goodsMapper;

    public PageDto getOrderList(Integer currentPage, Integer pageSize) {
        PageDto PageDto = new PageDto();
        List<OrderListDto> list = orderMapper.getAllOrder(currentPage - 1 , pageSize);
        PageDto.setRecords(list);
        PageDto.setTotal(list.size());
        return PageDto;
    }


    public PageDto updateOrder(Integer orderId , Integer flag) {
        Order order = orderMapper.selectById(orderId);
        order.setFlag(flag);
        orderMapper.updateById(order);
        return getOrderList(1,8);
    }

    public PageDto addOrder(Order order) {
        orderMapper.insert(order);
        return getOrderList(1,8);
    }

    @Transactional(rollbackFor = Exception.class)
    public PageDto deleteOrder(Integer orderId) {
        orderMapper.deleteById(orderId);
        return getOrderList(1,8);
    }

    public List<OrderListDto> searchOrder(Integer flag) {
        return orderMapper.getAllOrderByFlag(flag);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Goods> userAddOrder(Order order) {
        order.setOrderTime(new Date());
        orderMapper.insert(order);
        Goods goods = goodsMapper.selectById(order.getGoodsId());
        goods.setStock(goods.getStock() - order.getCount());
        goodsMapper.updateById(goods);
        return goodsMapper.selectList(null);
    }

    public List<OrderListDto> getMyOrder(Integer userId) {
        return orderMapper.getMyOrder(userId);
    }

    public List<OrderListDto> userUpdateOrderFlag(Integer orderId, Integer flag) {
        Order order = orderMapper.selectById(orderId);
        order.setFlag(flag);
        orderMapper.updateById(order);
        return orderMapper.getMyOrder(order.getUserId());
    }
}
