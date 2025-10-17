package com.petservices.dto;

import com.petservices.entity.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 创建于  2025-02-24
 *
 * @Version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingListDto extends Booking {

    private String name;

    private String serveName;

    private String phone;

}
