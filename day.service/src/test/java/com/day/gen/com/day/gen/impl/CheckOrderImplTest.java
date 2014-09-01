package com.day.gen.com.day.gen.impl;

import com.day.gen.CheckOrder;
import com.day.gen.Order;
import com.day.gen.OrderDao;
import com.day.gen.OrderDetail;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckOrderImplTest {


    private CheckOrder checkOrder;
    private int len = 5;

    @Before
    public void init(){
        CheckOrderImpl checkOrder = new CheckOrderImpl();
        checkOrder.setDao(new OrderDao() {
            @Override
            public Order getOrder(Date time, String vendor) {
                Order order = new Order();
                order.setAmount(100.00);
                order.setNum(11);
                return order;
            }

            @Override
            public List<OrderDetail> loadOrderDetail(Date time, String vendor) {
                List<OrderDetail> list = new ArrayList<OrderDetail>();
                for(int i =0; i< len; i++){
                    OrderDetail order = new OrderDetail();
                    order.setId(i+"");
                    order.setName("name"+i);
                    order.setPrice("price" + i);
                    list.add(order);
                }
                return list;
            }
        });
        this.checkOrder = checkOrder;
    }

    @Test
    public void createFile(){
        String templateHead = "shanghai.head.txt";
        String templateBody = "shanghai.body.txt";

        String vendor = "";

        String path = checkOrder.createFile(new Date(),templateHead, templateBody, vendor);

    }

}