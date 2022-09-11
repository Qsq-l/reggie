package org.com.qsqLt.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.com.qsqLt.common.R;
import org.com.qsqLt.entity.User;
import org.com.qsqLt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpServletRequest request){
        String phone = map.get("phone").toString();
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getPhone,phone);
        User user = userService.getOne(lambdaQueryWrapper);
        if(user == null){
            user = new User();
            user.setPhone(phone);
            userService.save(user);
        }
        request.getSession().setAttribute("user",user.getId());
        return R.success(user);
    }
}
