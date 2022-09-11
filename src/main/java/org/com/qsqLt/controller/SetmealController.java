package org.com.qsqLt.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.com.qsqLt.common.R;
import org.com.qsqLt.dto.SetmealDto;
import org.com.qsqLt.entity.Setmeal;
import org.com.qsqLt.entity.SetmealDish;
import org.com.qsqLt.service.CategoryService;
import org.com.qsqLt.service.SetmealDishService;
import org.com.qsqLt.service.SetmealService;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    private SetmealDishService setmealDishService;

    @Autowired
    private SetmealService setmealService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto){
        setmealService.saveWithDish(setmealDto);
        return R.success("成功");
    }
    @GetMapping("/page")
    public R<Page<SetmealDto>> page(int page,int pageSize,String name){
        Page<Setmeal> setmealPage = new Page<>();
        LambdaQueryWrapper<Setmeal> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(name != null,Setmeal::getName,name);
        lambdaQueryWrapper.orderByDesc(Setmeal::getUpdateTime);
        setmealService.page(setmealPage,lambdaQueryWrapper);

        List<SetmealDto> list = new ArrayList<>();
        list = setmealPage.getRecords().stream().map((item) -> {
            SetmealDto temp = new SetmealDto();
            BeanUtils.copyProperties(item,temp);
            temp.setCategoryName(categoryService.getById(item.getCategoryId()).getName());
            return temp;
        }).collect(Collectors.toList());
        Page<SetmealDto> setmealDtoPage = new Page<>();
        BeanUtils.copyProperties(setmealPage,setmealDtoPage,"records");
        setmealDtoPage.setRecords(list);
        return R.success(setmealDtoPage);
    }
    @DeleteMapping
    public R<String> delete(long[] ids){
        for(int i = 0;i<ids.length;i++){
            setmealService.removeById(ids[i]);
            LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(SetmealDish::getSetmealId,ids[i]);
            setmealDishService.remove(lambdaQueryWrapper);
        }
        return R.success("成功");
    }
    @GetMapping("/list")
    public R<List<Setmeal>> list(Setmeal setmeal){
        LambdaQueryWrapper<Setmeal> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Setmeal::getCategoryId,setmeal.getCategoryId());
        List<Setmeal> list = setmealService.list(lambdaQueryWrapper);
        return R.success(list);
    }
}
