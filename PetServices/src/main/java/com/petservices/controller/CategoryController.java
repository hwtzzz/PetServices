package com.petservices.controller;


import com.petservices.entity.Category;
import com.petservices.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2025-02-23
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    /**
     * 获取所有分类分页
     *
     * @return
     */
    @GetMapping("/getCategoryList")
    public List<Category> getCategoryList() {
        return categoryService.getCategoryList();
    }

    /**
     * 修改分类信息
     *
     * @param category
     */
    @PostMapping("/updateCategory")
    public void updateCategory(@RequestBody Category category) {
        categoryService.updateCategory(category);
    }

    /**
     * 添加分类
     *
     * @param category
     * @return
     */
    @PostMapping("/addCategory")
    public List<Category> addTeacher(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    /**
     * 删除分类信息
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/deleteCategory/{categoryId}")
    public List<Category> deleteCategory(@PathVariable Integer categoryId) {
        return categoryService.deleteCategory(categoryId);
    }

    /**
     * 查找分类
     *
     * @param categoryName
     * @return
     */
    @GetMapping("/searchCategory/{categoryName}")
    public List<Category> searchCategory(@PathVariable String categoryName) {
        return categoryService.searchCategory(categoryName);
    }

}

