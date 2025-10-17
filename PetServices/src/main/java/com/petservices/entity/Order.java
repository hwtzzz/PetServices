package com.petservices.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.petservices.config.AppConsts;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2025-02-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("orders")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "orderId", type = IdType.AUTO)
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

}
