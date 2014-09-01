package com.day.gen;

import java.util.Date;

/**
 * Created by day on 9/1/14.
 */
public interface CheckOrder {

    String createFile(Date time, String templateHead, String templateBody, String vendor);


}
