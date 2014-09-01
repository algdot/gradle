package com.day.gen;

import java.util.Date;
import java.util.List;

/**
 * Created by day on 9/1/14.
 */
public interface OrderDao {

    Order getOrder(Date time, String vendor);

    List<OrderDetail> loadOrderDetail(Date time, String vendor);

}
