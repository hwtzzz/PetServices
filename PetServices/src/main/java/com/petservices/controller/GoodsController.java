package com.petservices.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.petservices.entity.Goods;
import com.petservices.service.GoodsService;
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
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    /**
     * 获取所有商品分页
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/getGoodsList/{currentPage}/{pageSize}")
    public Page<Goods> getGoodsList(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        return goodsService.getGoodsList(currentPage, pageSize);
    }

    /**
     * 修改商品信息
     *
     * @param goods
     */
    @PostMapping("/updateGoods")
    public void updateGoods(@RequestBody Goods goods) {
        goodsService.updateGoods(goods);
    }

    /**
     * 添加商品
     *
     * @param goods
     * @return
     */
    @PostMapping("/addGoods")
    public Page<Goods> addGoods(@RequestBody Goods goods) {
        return goodsService.addGoods(goods);
    }

    /**
     * 删除商品信息
     *
     * @param goodsId
     * @return
     */
    @GetMapping("/deleteGoods/{goodsId}")
    public Page<Goods> deleteGoods(@PathVariable Integer goodsId) {
        return goodsService.deleteGoods(goodsId);
    }

    /**
     * 查找商品
     *
     * @param goodsName
     * @return
     */
    @GetMapping("/searchGoods/{goodsName}")
    public List<Goods> searchGoods(@PathVariable String goodsName) {
        return goodsService.searchGoods(goodsName);
    }

    @GetMapping("/getAllGoods")
    public List<Goods> getAllGoods() {
        return goodsService.getAllGoods();
    }
    
    
}

