package org.mendeleev.utils.http;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResponseUtils {

     public final static String OK_STATUE = "200 OK";
     public final static String NOT_FOUND_STATUE = "404 NOT FOUND";
     public final static String FORBIDDEN_STATUE = "403 FORBIDDEN";

     public static String currentDate(){
          SimpleDateFormat d = new SimpleDateFormat();
          return d.format(new Date());
     }

}
