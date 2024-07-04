package org.example.edu.dao.Custom.impl;

import javafx.collections.ObservableList;
import org.example.edu.dao.Custom.OrderDao;
import org.example.edu.entity.OrderEntity;
import org.example.edu.entity.OrderItemEntity;
import org.example.edu.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class OrderDaoImpl implements OrderDao {

    // OrderDaoImpl orderDao = DaoFactory.getInstance().getDao(DaoType. ORDER);
    @Override
    public OrderEntity search(String s) {
        return null;
    }

    @Override
    public ObservableList<OrderEntity> searchAll() {
        return null;
    }

    @Override
    public boolean insert(OrderEntity orderEntity) {

        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        session.persist(orderEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(OrderEntity orderEntity) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    public String getLatestId() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("SELECT id FROM order_table ORDER BY id DESC LIMIT 1");
        String id = (String) query.uniqueResult();
        session.close();
        return id;
    }

    public boolean saveAll(OrderItemEntity orderItemEntity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        session.persist(orderItemEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    private void updateQty(String itemCode, Integer qty) {

    }
}