package org.com.qsqLt.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.com.qsqLt.common.R;
import org.com.qsqLt.dto.DishDto;
import org.com.qsqLt.entity.Dish;
import org.com.qsqLt.entity.DishFlavor;
import org.com.qsqLt.service.CategoryService;
import org.com.qsqLt.service.DishFlavorService;
import org.com.qsqLt.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private DishFlavorService dishFlavorService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public R<String> save(@RequestBody DishDto dto){
        dishService.saveWithFlavors(dto);
        return R.success("保存成功");
    }

    @GetMapping("/page")
    public R<Page<DishDto>> page(int page,int pageSize,String name){
        Page<Dish> dishPage = new Page<>(page,pageSize);
        Page<DishDto> dtoPage = new Page<>(page,pageSize);
        LambdaQueryWrapper<Dish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(name != null, Dish::getName,name).orderByDesc(Dish::getUpdateTime);
        dishService.page(dishPage,lambdaQueryWrapper);

        BeanUtils.copyProperties(dishPage,dtoPage,"records");
        List<DishDto> list = dishPage.getRecords().stream().map((item) ->{
            DishDto temp = new DishDto();
            BeanUtils.copyProperties(item,temp);
            Long id = item.getCategoryId();
            String categoryName = categoryService.getById(id).getName();
            temp.setCategoryName(categoryName);
            return temp;
        }).collect(Collectors.toList());
        dtoPage.setRecords(list);
        return R.success(dtoPage);
    }
    @GetMapping("/{id}")
    public R<DishDto> getById(@PathVariable long id){
        DishDto dishDto = dishService.getWithFlavorById(id);
        return R.success(dishDto);
    }

    @PutMapping
    public R<String> update(@RequestBody DishDto dto){
        dishService.updateWithFlavor(dto);
        return R.success("修改成功");
    }
    @PostMapping("status/0")
    public R<String> close(long ids){
        Dish dish = dishService.getById(ids);
        dish.setStatus(0);
        dishService.updateById(dish);
        return R.success("修改成功");
    }
    @GetMapping("/list")
    public R<List<DishDto>> list(long categoryId){
        LambdaQueryWrapper<Dish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Dish::getCategoryId,categoryId);
        List<Dish> list = dishService.list(lambdaQueryWrapper);
        List<DishDto> dishDtoList = list.stream().map((item) -> {
            DishDto temp = new DishDto();
            BeanUtils.copyProperties(item,temp);
            Long id = item.getCategoryId();
            String categoryName = categoryService.getById(id).getName();
            temp.setCategoryName(categoryName);
            long dishId = item.getId();
            LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper1 = new LambdaQueryWrapper();
            lambdaQueryWrapper1.eq(DishFlavor::getDishId,dishId);
            List<DishFlavor> list1 = dishFlavorService.list(lambdaQueryWrapper1);
            temp.setFlavors(list1);
            return temp;
        }).collect(Collectors.toList());
        return R.success(dishDtoList);
    }
}
