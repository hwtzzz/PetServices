package com.petservices.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.petservices.dto.ServeChartsDto;
import com.petservices.entity.Category;
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
public interface CategoryMapper extends BaseMapper<Category> {

    @Select("" +
            "SELECT\n" +
            "\tc.categoryName,\n" +
            "\tCOUNT( s.serveId ) AS serveCount \n" +
            "FROM\n" +
            "\t`category` c\n" +
            "\tLEFT JOIN `serve` s ON c.categoryId = s.category \n" +
            "GROUP BY\n" +
            "\tc.categoryId,\n" +
            "\tc.categoryName;" +
            "")
    List<ServeChartsDto> selectServeCount();
}
