package com.petservices.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.petservices.entity.Serve;
import com.petservices.service.ServeService;
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
@RequestMapping("/serve")
public class ServeController {

    @Autowired
    ServeService serveService;

    /**
     * 获取所有服务分页
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/getServeList/{currentPage}/{pageSize}")
    public Page<Serve> getServeList(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        return serveService.getServeList(currentPage, pageSize);
    }

    /**
     * 修改服务信息
     *
     * @param serve
     */
    @PostMapping("/updateServe")
    public void updateServe(@RequestBody Serve serve) {
        serveService.updateServe(serve);
    }

    /**
     * 添加服务
     *
     * @param serve
     * @return
     */
    @PostMapping("/addServe")
    public Page<Serve> addServe(@RequestBody Serve serve) {
        return serveService.addServe(serve);
    }

    /**
     * 删除服务信息
     *
     * @param serveId
     * @return
     */
    @GetMapping("/deleteServe/{serveId}")
    public Page<Serve> deleteServe(@PathVariable Integer serveId) {
        return serveService.deleteServe(serveId);
    }

    /**
     * 查找服务
     *
     * @param serveName
     * @param category
     * @return
     */
    @PostMapping("/searchServe")
    public List<Serve> searchServe(@RequestBody Serve serve) {
        return serveService.searchServe(serve.getServeName() , serve.getCategory());
    }

    @GetMapping("/getAllServe")
    public List<Serve> getAllServe(){
        return serveService.getAllServe();
    }
    
}

