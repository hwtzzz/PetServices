package com.petservices.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.petservices.config.AppConsts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 平台活跃数
 * 创建于  2025-02-07
 *
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginChartsDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConsts.dateFormatPattern, timezone = "GMT+8")
    @DateTimeFormat(pattern = AppConsts.dateFormatPattern)
    private Date loginDate;

    private Integer totalLogin;

    private Integer adminLogin;

    private Integer userLogin;

}
