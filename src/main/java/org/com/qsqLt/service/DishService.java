package org.com.qsqLt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.com.qsqLt.common.R;
import org.com.qsqLt.dto.DishDto;
import org.com.qsqLt.entity.Dish;

public interface DishService extends IService<Dish> {
    public void saveWithFlavors(DishDto dto);
    public DishDto getWithFlavorById(long id);
    public void updateWithFlavor(DishDto dto);
}
