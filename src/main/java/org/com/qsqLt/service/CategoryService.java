package org.com.qsqLt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.com.qsqLt.entity.Category;

public interface CategoryService extends IService<Category> {
    public boolean delete(Long id);
}
