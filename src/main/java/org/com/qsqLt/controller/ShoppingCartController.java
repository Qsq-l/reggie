package org.com.qsqLt.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.com.qsqLt.common.BaseContext;
import org.com.qsqLt.common.R;
import org.com.qsqLt.entity.ShoppingCart;
import org.com.qsqLt.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart){
        shoppingCart.setUserId(BaseContext.getCurId());
        shoppingCart.setNumber(1);
        shoppingCartService.save(shoppingCart);
        return R.success(shoppingCart);
    }
    @GetMapping("list")
    public R<List<ShoppingCart>> list(){
        long userId = BaseContext.getCurId();
        LambdaQueryWrapper<ShoppingCart> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(ShoppingCart::getUserId,userId);
        List<ShoppingCart> list = shoppingCartService.list(lambdaQueryWrapper);
        return R.success(list);
    }
    @DeleteMapping("clean")
    public R<String> clean(){
        long id = BaseContext.getCurId();
        LambdaQueryWrapper<ShoppingCart> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(ShoppingCart::getUserId,id);
        shoppingCartService.remove(lambdaQueryWrapper);
        return R.success("成功");
    }
}
