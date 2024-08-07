package org.example.edu.bo.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.edu.bo.custom.OrderBo;
import org.example.edu.dao.Custom.impl.OrderDaoImpl;
import org.example.edu.dao.DaoFactory;
import org.example.edu.entity.OrderEntity;
import org.example.edu.entity.OrderItemEntity;
import org.example.edu.model.Order;
import org.example.edu.model.OrderItem;
import org.example.edu.util.DaoType;

import java.util.List;

public class OrderBoImpl implements OrderBo {

    OrderDaoImpl orderDaoImpl = DaoFactory.getInstance().getDao(DaoType.ORDER);

    public String generateOrderId() {


        String lastEmployeeId = orderDaoImpl.getLatestId();
        if (lastEmployeeId == null) {
            return "O0001";
        }

        int number = Integer.parseInt(lastEmployeeId.split("O")[1]);
        number++;
        return String.format("O%04d", number);
    }

    public boolean insertOrder(Order order) {

        OrderEntity orderEntity = new ObjectMapper().convertValue(order, OrderEntity.class);
        return orderDaoImpl.insert(orderEntity);
    }

    public boolean saveOrderDetails(ObservableList<OrderItem> orderItemObservableList) {
        orderItemObservableList.forEach(orderItem -> {
            OrderItemEntity orderItemEntity = new ObjectMapper().convertValue(orderItem, OrderItemEntity.class);
            orderDaoImpl.saveAll(orderItemEntity);

        });
        return true;
    }

    public Order getOrderById(String orderId) {
        OrderEntity orderEntity = orderDaoImpl.searchById(orderId);
        return new ObjectMapper().convertValue(orderEntity, Order.class);
    }

    public ObservableList<OrderItem> getOrderDetails() {

        List<OrderItemEntity> orderItemEntities = new OrderDaoImpl().searchOrderDetails();
        ObservableList<OrderItem> orderItems = FXCollections.observableArrayList();
        orderItemEntities.forEach(orderItemEntity -> {
            orderItems.add(new ObjectMapper().convertValue(orderItemEntity,OrderItem.class));
        });
        return orderItems;
    }
}
