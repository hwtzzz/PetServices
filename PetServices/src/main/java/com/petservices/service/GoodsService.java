package com.petservices.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.petservices.entity.Goods;
import com.petservices.entity.Goods;
import com.petservices.entity.Order;
import com.petservices.mapper.GoodsMapper;
import com.petservices.mapper.OrderMapper;
import com.petservices.utils.UserFriendlyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class GoodsService {

    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    OrderMapper orderMapper;

    public Page<Goods> getGoodsList(Integer currentPage, Integer pageSize) {
        Page<Goods> page = new Page<>(currentPage, pageSize);
        return goodsMapper.selectPage(page, null);
    }


    public void updateGoods(Goods goods) {
        goodsMapper.updateById(goods);
    }

    public Page<Goods> addGoods(Goods goods) {
        goodsMapper.insert(goods);
        Page<Goods> page = new Page<>(1, 5);
        return goodsMapper.selectPage(page, null);
    }

    @Transactional(rollbackFor = Exception.class)
    public Page<Goods> deleteGoods(Integer goodsId) {
        List<Order> orders = orderMapper.selectList(new LambdaQueryWrapper<Order>().eq(Order::getGoodsId, goodsId));
        UserFriendlyException.throwException(orders.size() > 0 , "请先删除对应的订单的信息!");
        goodsMapper.deleteById(goodsId);
        Page<Goods> page = new Page<>(1, 5);
        return goodsMapper.selectPage(page, null);
    }

    public List<Goods> searchGoods(String goodsName) {
        return goodsMapper.selectList(new LambdaQueryWrapper<Goods>()
                .like(Goods::getGoodsName , goodsName));
    }

    public List<Goods> getAllGoods() {
        return goodsMapper.selectList(null);
    }
}
