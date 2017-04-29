package com.web.service;

import com.web.dao.IClassDAO;
import com.web.dao.IOrderDAO;
import com.web.entity.backend.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by duyle on 17/02/2017.
 */
@Service
public class OrderService implements IOrderService{

    @Autowired
    private IOrderDAO orderDAO;

    public List<Orders> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    public Orders getOrderById(int id) {
        return orderDAO.getOrderById(id);
    }

    public int addOrder(Orders orders) {
        return orderDAO.addOrder(orders);
    }

    public void updateOrder(Orders orders) {
        orderDAO.updateOrder(orders);
    }

    public boolean isOrderExistByOrder(Orders orders) {
        if (orderDAO.isOrderExistByOrder(orders)) {
            return true;
        } else {
            return false;
        }
    }

    public List<Orders> getOrdersCreateOrUpdateLast(Date date) {
        return orderDAO.getOrdersCreateOrUpdateLast(date);
    }

    public Timestamp getDateUpdateLastest() {
        return orderDAO.getDateUpdateLastest();
    }

    public List<Orders> getOrderByClassId(int id) {
        return orderDAO.getOrderByClassId(id);
    }
}
