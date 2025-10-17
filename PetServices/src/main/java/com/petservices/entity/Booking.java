package com.petservices.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("booking")
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "bookingId", type = IdType.AUTO)
    private Integer bookingId;

    /**
     * 预约时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConsts.dateFormatPattern)
    @DateTimeFormat(pattern = AppConsts.dateFormatPattern)
    private Date bookingDate;

    /**
     * 预约人
     */
    private Integer userId;

    /**
     * 预约服务
     */
    private Integer serve;

    /**
     * 评价内容
     */
    private String appraise;

    /**
     * 打分
     */
    private Integer star;

    /**
     * 预约状态 1预约中 2已服务 3已评价
     */
    private Integer flag;


}
