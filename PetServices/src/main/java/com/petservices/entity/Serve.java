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
@TableName("serve")
public class Serve implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "serveId", type = IdType.AUTO)
    private Integer serveId;

    /**
     * 服务名称
     */
    private String serveName;

    /**
     * 服务类型
     */
    private Integer category;

    /**
     * 服务价格
     */
    private Integer price;

    /**
     * 服务描述
     */
    private String serveDesc;

    /**
     * 服务图片
     */
    private String serveImage;


}
