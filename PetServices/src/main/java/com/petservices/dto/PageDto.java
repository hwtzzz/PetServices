package com.petservices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Auther 李忠妹
 * 创建于  2024-12-09
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDto {

    private Integer total;

    private List<?> records;

}
