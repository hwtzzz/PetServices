package com.petservices.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.petservices.dto.LoginChartsDto;
import com.petservices.entity.LoginInfo;
import org.apache.ibatis.annotations.Mapper;
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
public interface LoginInfoMapper extends BaseMapper<LoginInfo> {

    @Select("" +
            "SELECT DATE\n" +
            "\t( loginTime ) AS loginDate,\n" +
            "\tCOUNT(*) AS totalLogin,\n" +
            "\tSUM( CASE WHEN role = 1 THEN 1 ELSE 0 END ) AS adminLogin,\n" +
            "\tSUM( CASE WHEN role = 2 THEN 1 ELSE 0 END ) AS userLogin \n" +
            "FROM\n" +
            "\tlogin_info \n" +
            "WHERE\n" +
            "\tloginTime >= CURDATE() - INTERVAL 30 DAY \n" +
            "GROUP BY\n" +
            "\tDATE ( loginTime ) \n" +
            "ORDER BY\n" +
            "\tloginDate DESC;" +
            "")
    List<LoginChartsDto> selectLoginInfo();
}
