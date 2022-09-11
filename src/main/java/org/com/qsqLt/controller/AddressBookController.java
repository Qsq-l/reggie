package org.com.qsqLt.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.com.qsqLt.common.BaseContext;
import org.com.qsqLt.common.R;
import org.com.qsqLt.entity.AddressBook;
import org.com.qsqLt.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("addressBook")
public class AddressBookController {
    @Autowired
    public AddressBookService addressBookService;

    @PostMapping
    public R<String> add(@RequestBody AddressBook addressBook, HttpServletRequest request){

        long userId = (long)request.getSession().getAttribute("user");
        addressBook.setUserId(userId);
        addressBookService.save(addressBook);
        return R.success("成功");
    }
    @GetMapping("/list")
    public R<List<AddressBook>> page(){
        Page<AddressBook> page = new Page<>();
        LambdaQueryWrapper<AddressBook> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        long userId = BaseContext.getCurId();
        lambdaQueryWrapper.eq(AddressBook::getUserId,userId);
        lambdaQueryWrapper.orderByDesc(AddressBook::getUpdateTime);
        return R.success(addressBookService.list(lambdaQueryWrapper));
    }

    @PutMapping("/default")
    public R<AddressBook> update(@RequestBody AddressBook addressBook){
        LambdaQueryWrapper<AddressBook> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        addressBook.setIsDefault(1);
        addressBookService.updateById(addressBook);
        return R.success(addressBook);
    }
    @GetMapping("/default")
    public R<AddressBook> show(){
        LambdaQueryWrapper<AddressBook> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(AddressBook::getUserId,BaseContext.getCurId());
        AddressBook addressBook = addressBookService.getOne(lambdaQueryWrapper);
        return R.success(addressBook);
    }
}
