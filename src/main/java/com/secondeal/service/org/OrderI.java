package com.secondeal.service.org;

import com.secondeal.model.org.Order;

import java.util.List;

public interface OrderI {
    int insert(Order order);

    List<Order> getByBuyUuid(String uuid);
}
