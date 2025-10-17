package com.petservices.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.petservices.dto.OrderListDto;
import com.petservices.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2025-02-23
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    @Select("" +
            "SELECT\n" +
            "\to.*,\n" +
            "\tu.NAME,\n" +
            "\tu.address,\n" +
            "\tg.goodsName \n" +
            "FROM\n" +
            "\torders o\n" +
            "\tLEFT JOIN USER u ON o.userId = u.userId\n" +
            "\tLEFT JOIN goods g ON o.goodsId = g.goodsId limit #{currentPage},#{pageSize}" +
            "")
    List<OrderListDto> getAllOrder(@Param("currentPage") Integer currentPage,@Param("pageSize") Integer pageSize);

    @Select("" +
            "SELECT\n" +
            "\to.*,\n" +
            "\tu.NAME,\n" +
            "\tu.address,\n" +
            "\tg.goodsName \n" +
            "FROM\n" +
            "\torders o\n" +
            "\tLEFT JOIN USER u ON o.userId = u.userId\n" +
            "\tLEFT JOIN goods g ON o.goodsId = g.goodsId where o.flag = #{flag}" +
            "")
    List<OrderListDto> getAllOrderByFlag(@Param("flag") Integer flag);

    @Select("" +
            "SELECT\n" +
            "\to.*,\n" +
            "\tu.NAME,\n" +
            "\tu.address,\n" +
            "\tg.goodsName \n" +
            "FROM\n" +
            "\torders o\n" +
            "\tLEFT JOIN USER u ON o.userId = u.userId\n" +
            "\tLEFT JOIN goods g ON o.goodsId = g.goodsId where o.userId = #{userId}" +
            "")
    List<OrderListDto> getMyOrder(@Param("userId") Integer userId);
}
