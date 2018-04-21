package com.secondeal.dao.org;

import com.secondeal.model.org.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDao {

    int insert(Order record);

    List<Order> getByBuyUuid(@Param("buy_uuid") String uuid);

}