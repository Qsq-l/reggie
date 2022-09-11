package org.com.qsqLt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.com.qsqLt.entity.OrderDetail;
import org.com.qsqLt.mapper.OrderDetailMapper;
import org.com.qsqLt.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
