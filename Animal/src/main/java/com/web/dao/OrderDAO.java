package com.web.dao;

import com.web.entity.backend.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by duyle on 17/02/2017.
 */

@Transactional
@Repository
public class OrderDAO implements IOrderDAO {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public List<Orders> getAllOrders() {
        String hql = "FROM Orders as p ORDER BY p.id";
        
        hibernateTemplate.setMaxResults(0);
        return (List<Orders>) hibernateTemplate.find(hql);
    }

    public Orders getOrderById(int id) {
        
        hibernateTemplate.setMaxResults(0);
        return hibernateTemplate.get(Orders.class, id);
    }

    public int addOrder(Orders orders) {
        return (Integer) hibernateTemplate.save(orders);
    }

    public void updateOrder(Orders orders) {
        Orders p = getOrderById(orders.getId());
        p.setNotation(orders.getNotation());
        p.setStatus(orders.getStatus());
        p.setScienceName(orders.getScienceName());
        p.setVietnameseName(orders.getVietnameseName());
        p.setYearDiscover(orders.getYearDiscover());
        p.setDiscovererName(orders.getDiscovererName());
        p.setIdCreator(orders.getIdCreator());
        p.setIdChecker(orders.getIdChecker());
        p.setDateCreate(orders.getDateCreate());
        p.setDateUpdate(orders.getDateUpdate());
        p.setIdClass(orders.getIdClass());
        hibernateTemplate.merge(orders);
    }

    public boolean isOrderExistByOrder(Orders orders) {
        String hql = "FROM Orders Where scienceName = '" + orders.getScienceName() + "' or vietnameseName = '" + orders.getVietnameseName() + "'";
        
        hibernateTemplate.setMaxResults(0);
        List<Orders> ordersList = (List<Orders>) hibernateTemplate.find(hql);
        if (ordersList.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public List<Orders> getOrdersCreateOrUpdateLast(Date date) {
        String hql = "FROM Orders as p WHERE p.dateUpdate > '" + date + "' ORDER BY p.id";
//        hibernateTemplate.setMaxResults(200);
        
        hibernateTemplate.setMaxResults(0);
        return (List<Orders>) hibernateTemplate.find(hql);
    }

    public Timestamp getDateUpdateLastest() {
        String hql = "FROM Orders as p ORDER BY p.dateUpdate DESC";
        
        hibernateTemplate.setMaxResults(1);
        List<Orders> ordersList = (List<Orders>) hibernateTemplate.find(hql);
        return ordersList.get(0).getDateUpdate();
    }

    public List<Orders> getOrderByClassId(int id) {
        String hql = "FROM Orders as p WHERE idClass.id = " + id + " ORDER BY p.id";
        
        hibernateTemplate.setMaxResults(0);
        return (List<Orders>) hibernateTemplate.find(hql);
    }
}
