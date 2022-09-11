package org.com.qsqLt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.com.qsqLt.entity.DishFlavor;
import org.com.qsqLt.mapper.DishFlavorMapper;
import org.com.qsqLt.service.DishFlavorService;
import org.com.qsqLt.service.DishService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
