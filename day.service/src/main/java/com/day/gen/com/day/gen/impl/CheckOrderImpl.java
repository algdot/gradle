package com.day.gen.com.day.gen.impl;

import com.day.gen.CheckOrder;
import com.day.gen.Order;
import com.day.gen.OrderDao;
import com.day.gen.OrderDetail;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.List;

/**
 * Created by day on 9/1/14.
 */
public class CheckOrderImpl implements CheckOrder {

    private File file;

    private OrderDao dao;

    public void setDao(OrderDao dao) {
        this.dao = dao;
    }

    private String getTemplateFilePath(String template){
        String baseDir = CheckOrderImpl.class.getResource("/").toString();

        return baseDir+ "/"+template;
    }

    private Order loadHead(Date time, String vendor){
        return dao.getOrder(time, vendor);

    }
    private List<OrderDetail> loadBody(Date time, String vendor){
        return dao.loadOrderDetail(time, vendor);
    }

    @Override
    public String createFile(Date time, String templateHead, String templateBody, String vendor) {
        Order order = loadHead(time, vendor);
        List<OrderDetail> list = this.loadBody(time, vendor);

        Configuration cfg = new Configuration();
        Template template = null;
        try {
            template = cfg.getTemplate(getTemplateFilePath(templateHead));

            Writer out = new OutputStreamWriter(System.out);
            template.process(order, out);
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }


        return null;
    }
}
