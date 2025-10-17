package com.petservices.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.petservices.config.AppConsts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 创建于  2025-02-25
 *
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListDto {

    private Integer orderId;

    /**
     * 商品信息
     */
    private Integer goodsId;

    /**
     * 下单用户
     */
    private Integer userId;

    /**
     * 下单时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConsts.dateTimeFormatPattern, timezone = "GMT+8")
    @DateTimeFormat(pattern = AppConsts.dateTimeFormatPattern)
    private Date orderTime;

    /**
     * 订单状态 1已下单 2送货中 3已收货
     */
    private Integer flag;

    /**
     * 商品数量
     */
    private Integer count;

    /**
     * 总金额
     */
    private String totalPrice;

    private String name;

    private String address;

    private String goodsName;

}
