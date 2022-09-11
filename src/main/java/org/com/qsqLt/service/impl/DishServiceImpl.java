package org.com.qsqLt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.com.qsqLt.dto.DishDto;
import org.com.qsqLt.entity.Dish;
import org.com.qsqLt.entity.DishFlavor;
import org.com.qsqLt.mapper.DishMapper;
import org.com.qsqLt.service.DishFlavorService;
import org.com.qsqLt.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * 因为涉及多张表操作，加入注释Transactional开启事务
     * @param dto
     */
    @Override
    @Transactional
    public void saveWithFlavors(DishDto dto) {
        this.save(dto);
        List<DishFlavor> list = dto.getFlavors();
        Long dishId = dto.getId();
        list.stream().map((item) ->{
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(list);
    }

    @Override
    public DishDto getWithFlavorById(long id) {
        Dish dish = this.getById(id);
        LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DishFlavor::getDishId,id);
        List<DishFlavor> list = dishFlavorService.list(lambdaQueryWrapper);
        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish,dishDto);
        dishDto.setFlavors(list);
        return dishDto;
    }

    @Transactional
    @Override
    public void updateWithFlavor(DishDto dto) {

        this.updateById(dto);

        LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId,dto.getId());
        dishFlavorService.remove(dishFlavorLambdaQueryWrapper);

        List<DishFlavor> dishFlavors = dto.getFlavors();
        dishFlavors = dishFlavors.stream().map((item) ->{
            item.setDishId(dto.getId());
            return item;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(dishFlavors);
    }
}
