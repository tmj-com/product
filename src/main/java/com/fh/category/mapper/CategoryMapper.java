package com.fh.category.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.model.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    List queryListByPid(Integer pid);

    void addCategory(Category category);

    List queryList();

    void updateCategory(Category category);

    void deleteCategory(List list);
}
