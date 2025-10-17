package com.petservices.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@TableName("goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "goodsId", type = IdType.AUTO)
        private Integer goodsId;

    /**
     * 商品名
     */
    private String goodsName;

    /**
     * 商品价格
     */
    private String price;

    /**
     * 商品图片
     */
    private String goodsImage;

    /**
     * 商品描述
     */
    private String goodsDesc;

    /**
     * 库存
     */
    private Integer stock;


}
