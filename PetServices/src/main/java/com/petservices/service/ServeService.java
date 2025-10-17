package com.petservices.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.petservices.entity.Booking;
import com.petservices.entity.Serve;
import com.petservices.mapper.BookingMapper;
import com.petservices.mapper.ServeMapper;
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
public class ServeService {

    @Autowired
    ServeMapper serveMapper;
    @Autowired
    BookingMapper bookingMapper;

    public Page<Serve> getServeList(Integer currentPage, Integer pageSize) {
        Page<Serve> userPage = new Page<>(currentPage, pageSize);
        return serveMapper.selectPage(userPage, null);
    }


    public void updateServe(Serve serve) {
        serveMapper.updateById(serve);
    }

    public Page<Serve> addServe(Serve serve) {
        serveMapper.insert(serve);
        Page<Serve> page = new Page<>(1, 5);
        return serveMapper.selectPage(page, null);
    }

    @Transactional(rollbackFor = Exception.class)
    public Page<Serve> deleteServe(Integer serveId) {
        //查找是否有对应的服务预约
        List<Booking> bookings = bookingMapper.selectList(new LambdaQueryWrapper<Booking>().eq(Booking::getServe, serveId));
        UserFriendlyException.throwException(bookings.size() > 0 , "请先删除对应的服务预约信息!");
        serveMapper.deleteById(serveId);
        Page<Serve> page = new Page<>(1, 5);
        return serveMapper.selectPage(page, null);
    }

    public List<Serve> searchServe(String serveName,Integer category) {
        QueryWrapper<Serve> w = new QueryWrapper<>();
        if (StringUtils.isNotBlank(serveName)) {
            w.like("serveName", serveName);
        }
        if (category != null) {
            w.eq("category", category);
        }
        return serveMapper.selectList(w);
    }

    public List<Serve> getAllServe() {
        return serveMapper.selectList(null);
    }
}
