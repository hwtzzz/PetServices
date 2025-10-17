package com.petservices.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.petservices.dto.ChartsInfo;
import com.petservices.dto.LoginChartsDto;
import com.petservices.dto.ServeChartsDto;
import com.petservices.entity.Admin;
import com.petservices.entity.LoginInfo;
import com.petservices.entity.User;
import com.petservices.mapper.*;
import com.petservices.utils.UserFriendlyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
@RequiredArgsConstructor
public class AdminService {

    final AdminMapper adminMapper;
    final LoginInfoMapper loginInfoMapper;
    final UserMapper userMapper;
    final OrderMapper orderMapper;
    final ServeMapper serveMapper;
    final BookingMapper bookingMapper;
    final CategoryMapper categoryMapper;

    public Admin login(String username, String password) {
        Admin admin = adminMapper.selectOne(new LambdaQueryWrapper<Admin>()
                .eq(Admin::getUsername, username)
                .eq(Admin::getPassword, password));
        UserFriendlyException.throwException(admin == null, "用户名或密码错误!");
        //存放登录信息
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setLoginTime(new Date());
        loginInfo.setLoginUsername(admin.getUsername());
        loginInfo.setLoginName(admin.getName());
        loginInfo.setPhone(admin.getPhone());
        loginInfo.setRole(2);
        loginInfoMapper.insert(loginInfo);
        return admin;
    }

    public Page<Admin> getAdminList(Integer currentPage, Integer pageSize) {
        Page<Admin> userPage = new Page<>(currentPage, pageSize);
        return adminMapper.selectPage(userPage, null);
    }


    public void updateAdmin(Admin admin) {
        adminMapper.updateById(admin);
    }

    public boolean ruleStr(String str) {
        return str.length() < 6;
    }

    public Page<Admin> addAdmin(Admin admin) {
        //验证用户名密码
        UserFriendlyException.throwException(ruleStr(admin.getUsername()), "用户名长度不能小于6!");
        //设置初始密码
        admin.setPassword("123456");
        Admin e = adminMapper.selectOne(new LambdaQueryWrapper<Admin>().eq(Admin::getUsername, admin.getUsername()));
        UserFriendlyException.throwException(e != null, "此用户名已被注册，请重新输入!");
        adminMapper.insert(admin);
        Page<Admin> userPage = new Page<>(1, 5);
        return adminMapper.selectPage(userPage, null);
    }

    @Transactional(rollbackFor = Exception.class)
    public Page<Admin> deleteAdmin(Integer adminId) {
        adminMapper.deleteById(adminId);
        Page<Admin> userPage = new Page<>(1, 5);
        return adminMapper.selectPage(userPage, null);
    }

    public List<Admin> searchAdmin(String name, String phone) {
        QueryWrapper<Admin> w = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            w.like("name", name);
        }
        if (StringUtils.isNotBlank(phone)) {
            w.eq("phone", phone);
        }
        return adminMapper.selectList(w);
    }

    public Page<Admin> resetPassword(Integer adminId) {
        Admin admin = adminMapper.selectById(adminId);
        admin.setPassword("123456");
        adminMapper.updateById(admin);
        Page<Admin> userPage = new Page<>(1, 5);
        return adminMapper.selectPage(userPage, null);
    }

    public void updatePassword(String userType, Integer id, String password) {
        if (userType.equals("admin")) {
            Admin admin = adminMapper.selectById(id);
            admin.setPassword(password);
            adminMapper.updateById(admin);
        }
        if (userType.equals("user")) {
            User user = userMapper.selectById(id);
            user.setPassword(password);
            userMapper.updateById(user);
        }
    }

    public ChartsInfo getDataInfo() {
        ChartsInfo chartsInfo = new ChartsInfo();
        //系统总人数
        Integer admins = adminMapper.selectCount(null);
        Integer users = userMapper.selectCount(null);
        chartsInfo.setTotalPeople(admins + users);
        //系统预约数
        Integer bookings = bookingMapper.selectCount(null);
        chartsInfo.setBookingTotal(bookings);
        //系统订单数
        Integer orders = orderMapper.selectCount(null);
        chartsInfo.setOrderTotal(orders);
        //系统服务数
        Integer serves = serveMapper.selectCount(null);
        chartsInfo.setServeTotal(serves);
        //各分类服务数量
        List<ServeChartsDto> list = categoryMapper.selectServeCount();
        chartsInfo.setServeList(list);
        //获取近一个月社区活跃数
        List<LoginChartsDto> loginChartsDtos = loginInfoMapper.selectLoginInfo();
        chartsInfo.setLoginChartsList(loginChartsDtos);
        return chartsInfo;
    }
}
