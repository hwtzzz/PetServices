package com.petservices.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.petservices.entity.Category;
import com.petservices.entity.Serve;
import com.petservices.mapper.CategoryMapper;
import com.petservices.mapper.ServeMapper;
import com.petservices.utils.UserFriendlyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class CategoryService {

    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    ServeMapper serveMapper;

    public List<Category> getCategoryList() {
        return categoryMapper.selectList(null);
    }
    public void updateCategory(Category category) {
        categoryMapper.updateById(category);
    }
    public List<Category> addCategory(Category category) {
        categoryMapper.insert(category);
        return getCategoryList();
    }
    public List<Category> deleteCategory(Integer categoryId) {
        List<Serve> serves = serveMapper.selectList(new LambdaQueryWrapper<Serve>()
                .eq(Serve::getCategory, categoryId));
        UserFriendlyException.throwException(serves.size() > 0 , "清新删除分类下的服务!");
        categoryMapper.deleteById(categoryId);
        return getCategoryList();
    }
    public List<Category> searchCategory(String categoryName) {
        return categoryMapper.selectList(new LambdaQueryWrapper<Category>()
                .like(Category::getCategoryName, categoryName));
    }

}
