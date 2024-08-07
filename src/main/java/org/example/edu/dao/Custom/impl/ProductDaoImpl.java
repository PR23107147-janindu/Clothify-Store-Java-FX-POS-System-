package org.example.edu.dao.Custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.edu.dao.Custom.ProductDao;
import org.example.edu.entity.ProductEntity;
import org.example.edu.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDaoImpl implements ProductDao {


    public ProductEntity search(String s) {
        return null;
    }


    public ObservableList<ProductEntity> searchAll() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<ProductEntity> list = session.createQuery("FROM product").list();
        session.close();

        ObservableList<ProductEntity> customerEntityList = FXCollections.observableArrayList();

        list.forEach(productEntity -> {
            customerEntityList.add(productEntity);
        });
        return customerEntityList;
    }

    @Override
    public ProductEntity Search(String s) {
        return null;
    }

    @Override
    public ObservableList<ProductEntity> SearchAll() {
        return null;
    }

    @Override
    public boolean insert(ProductEntity productEntity) {

        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        session.persist(productEntity);
        session.getTransaction().commit();
        session.close();
        return true;

    }

    @Override
    public boolean update(ProductEntity productEntity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("UPDATE product SET name =:name,qty =:qty,size =:size ,price= :price ,supId =:supId WHERE id =:id");
        query.setParameter("id",productEntity.getId());
        query.setParameter("name",productEntity.getName());
        query.setParameter("qty",productEntity.getQty());
        query.setParameter("size",productEntity.getSize());
        query.setParameter("price",productEntity.getPrice());
        query.setParameter("supId",productEntity.getSupId());


        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return i>0;


    }

    @Override
    public boolean delete(String s) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("DELETE FROM product WHERE id=:id");
        query.setParameter("id",s);
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();

        return i>0;
    }

    public String getLatestId() {

        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("SELECT id FROM product ORDER BY id DESC LIMIT 1");
        String id = (String) query.uniqueResult();
        session.close();
        return id;
    }

    public ProductEntity searchById(String id) {

        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("FROM product WHERE id=:id");
        query.setParameter("id",id);
        ProductEntity productEntity = (ProductEntity) query.uniqueResult();
        session.close();

        return productEntity;
    }
}
