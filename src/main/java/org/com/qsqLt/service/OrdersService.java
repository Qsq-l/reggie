package org.com.qsqLt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.com.qsqLt.common.R;
import org.com.qsqLt.entity.Orders;

public interface OrdersService extends IService<Orders> {
    public void submit(Orders orders);
}
