package com.petservices.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.petservices.dto.ChartsInfo;
import com.petservices.entity.Admin;
import com.petservices.service.AdminService;
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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    /**
     * 管理员登录
     * @param username
     * @param password
     * @return
     */
    @GetMapping("/login/{username}/{password}")
    public Admin login(@PathVariable String username , @PathVariable String password){
        return adminService.login(username , password);
    }

    /**
     * 获取所有管理员分页
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/getAdminList/{currentPage}/{pageSize}")
    public Page<Admin> getAdminList(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        return adminService.getAdminList(currentPage, pageSize);
    }

    /**
     * 修改管理员信息
     *
     * @param admin
     */
    @PostMapping("/updateAdmin")
    public void updateAdmin(@RequestBody Admin admin) {
        adminService.updateAdmin(admin);
    }

    /**
     * 添加管理员
     *
     * @param admin
     * @return
     */
    @PostMapping("/addAdmin")
    public Page<Admin> addAdmin(@RequestBody Admin admin) {
        return adminService.addAdmin(admin);
    }

    /**
     * 删除管理员信息
     *
     * @param adminId
     * @return
     */
    @GetMapping("/deleteAdmin/{adminId}")
    public Page<Admin> deleteAdmin(@PathVariable Integer adminId) {
        return adminService.deleteAdmin(adminId);
    }

    /**
     * 查找管理员
     *
     * @param admin
     * @return
     */
    @PostMapping("/searchAdmin")
    public List<Admin> searchAdmin(@RequestBody Admin admin) {
        return adminService.searchAdmin(admin.getName(), admin.getPhone());
    }

    /**
     * 修改管理员密码
     *
     * @param adminId
     * @return
     */
    @GetMapping("/resetPassword/{adminId}")
    public Page<Admin> resetPassword(@PathVariable Integer adminId) {
        return adminService.resetPassword(adminId);
    }

    /**
     * 这个修改密码的方法所有人都可以使用
     * @param userType
     * @param id
     * @param password
     */
    @GetMapping("/updatePassword/{userType}/{id}/{password}")
    public void updatePassword(@PathVariable String userType , @PathVariable Integer id , @PathVariable String password) {
        adminService.updatePassword(userType , id , password);
    }

    @GetMapping("/getDataInfo")
    public ChartsInfo getDataInfo(){
        return adminService.getDataInfo();
    }

}

