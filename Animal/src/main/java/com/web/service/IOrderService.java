package com.web.service;

import com.web.entity.backend.Orders;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by duyle on 17/02/2017.
 */

public interface IOrderService {

    List<Orders> getAllOrders();

    Orders getOrderById(int id);

    int addOrder(Orders orders);

    void updateOrder(Orders orders);

    boolean isOrderExistByOrder(Orders orders);

    List<Orders> getOrdersCreateOrUpdateLast(Date date);

    Timestamp getDateUpdateLastest();

    List<Orders> getOrderByClassId(int id);
}
