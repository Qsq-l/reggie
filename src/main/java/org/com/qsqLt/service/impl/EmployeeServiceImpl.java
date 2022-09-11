package org.com.qsqLt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.com.qsqLt.entity.Employee;
import org.com.qsqLt.mapper.EmployeeMapper;
import org.com.qsqLt.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {


}
