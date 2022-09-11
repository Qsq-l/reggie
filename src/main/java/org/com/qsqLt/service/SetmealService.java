package org.com.qsqLt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.com.qsqLt.dto.SetmealDto;
import org.com.qsqLt.entity.Setmeal;
import org.com.qsqLt.entity.SetmealDish;

public interface SetmealService extends IService<Setmeal> {
    public void saveWithDish(SetmealDto setmealDto);
}
