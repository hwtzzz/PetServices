package com.petservices.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.petservices.dto.BookingListDto;
import com.petservices.dto.PageDto;
import com.petservices.entity.Booking;
import com.petservices.service.BookingService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2025-02-23
 */
@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    /**
     * 获取所有预约分页
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/getBookingList/{currentPage}/{pageSize}")
    public PageDto getBookingList(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        return bookingService.getBookingList(currentPage, pageSize);
    }

    /**
     * 修改预约信息
     *
     * @param bookingId
     * @param flag
     */
    @GetMapping("/updateBookingFlag/{bookingId}/{flag}")
    public PageDto updateBooking(@PathVariable("bookingId") Integer bookingId , @PathVariable("flag") Integer flag) {
        return bookingService.updateBooking(bookingId , flag);
    }

    /**
     * 添加预约
     *
     * @param booking
     * @return
     */
    @PostMapping("/addBooking")
    public void addBooking(@RequestBody Booking booking) {
        bookingService.addBooking(booking);
    }

    /**
     * 删除预约信息
     *
     * @param bookingId
     * @return
     */
    @GetMapping("/deleteBooking/{bookingId}")
    public PageDto deleteBooking(@PathVariable Integer bookingId) {
        return bookingService.deleteBooking(bookingId);
    }

    /**
     * 查找预约
     *
     * @param flag
     * @return
     */
    @GetMapping("/searchBooking/{flag}")
    public List<BookingListDto> searchBooking(@PathVariable Integer flag) {
        return bookingService.searchBooking(flag);
    }

    //前台
    @GetMapping("/getMyBooking/{userId}")
    public List<BookingListDto> getMyBooking(@PathVariable Integer userId) {
        return bookingService.getMyBooking(userId);
    }

    @PostMapping("/updateBookingComment")
    public List<BookingListDto> updateBookingComment(@RequestBody Booking booking) {
        return bookingService.updateBookingComment(booking);
    }
}

