package com.petservices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 创建于  2025-02-24
 *
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChartsInfo {

    private Integer totalPeople;

    private Integer bookingTotal;

    private Integer orderTotal;

    private Integer serveTotal;

    private List<ServeChartsDto> serveList;

    private List<LoginChartsDto> loginChartsList;

}
