package org.com.qsqLt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.com.qsqLt.entity.ShoppingCart;
import org.com.qsqLt.mapper.ShoppingCartMapper;
import org.com.qsqLt.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
