package com.fh.category.service;


import com.fh.model.Category;

import java.util.List;

public interface CategoryService {
    List queryListByPid(Integer pid);

    List queryList();

    void addCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(List idList);
}
