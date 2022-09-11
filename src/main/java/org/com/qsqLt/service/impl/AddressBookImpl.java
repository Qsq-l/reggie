package org.com.qsqLt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.com.qsqLt.entity.AddressBook;
import org.com.qsqLt.mapper.AddressBookMapper;
import org.com.qsqLt.service.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
