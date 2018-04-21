package com.secondeal.service.impl.org;

import com.secondeal.dao.account.AccountDao;
import com.secondeal.dao.org.OrderDao;
import com.secondeal.model.org.Order;
import com.secondeal.service.org.OrderI;
import com.secondeal.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderIImpl implements OrderI {
    @Autowired
    private OrderDao orderDao;

    @Override
    public int insert(Order order) {
        return orderDao.insert(order);
    }

    @Override
    public List<Order> getByBuyUuid(String uuid) {
        return orderDao.getByBuyUuid(uuid);
    }
}
