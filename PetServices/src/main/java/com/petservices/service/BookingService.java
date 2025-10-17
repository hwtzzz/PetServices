package com.petservices.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.petservices.dto.BookingListDto;
import com.petservices.dto.PageDto;
import com.petservices.entity.Booking;
import com.petservices.entity.Booking;
import com.petservices.entity.Order;
import com.petservices.mapper.BookingMapper;
import com.petservices.utils.UserFriendlyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ${author}
 * @since 2025-02-23
 */
@Service
public class BookingService {

    @Autowired
    BookingMapper bookingMapper;

    public PageDto getBookingList(Integer currentPage, Integer pageSize) {
        PageDto PageDto = new PageDto();
        List<BookingListDto> list = bookingMapper.getAllBooking(currentPage - 1 , pageSize);
        PageDto.setRecords(list);
        PageDto.setTotal(list.size());
        return PageDto;
    }


    public PageDto updateBooking(Integer bookingId , Integer flag) {
        Booking booking = bookingMapper.selectById(bookingId);
        booking.setFlag(flag);
        bookingMapper.updateById(booking);
        return getBookingList(1,8);
    }

    public void addBooking(Booking booking) {
        booking.setFlag(1);
        bookingMapper.insert(booking);
    }

    @Transactional(rollbackFor = Exception.class)
    public PageDto deleteBooking(Integer bookingId) {
        bookingMapper.deleteById(bookingId);
        return getBookingList(1,8);
    }

    public List<BookingListDto> searchBooking(Integer flag) {
        return bookingMapper.getAllBookingByFlag(flag);
    }

    public List<BookingListDto> getMyBooking(Integer userId) {
        return bookingMapper.getMyBooking(userId);
    }

    public List<BookingListDto> updateBookingComment(Booking booking) {
        Booking b = bookingMapper.selectById(booking.getBookingId());
        b.setAppraise(booking.getAppraise());
        b.setStar(booking.getStar());
        b.setFlag(3);
        bookingMapper.updateById(b);
        return getMyBooking(b.getUserId());
    }
}
