package org.mendeleev.tuition;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SQLWithSpring {

     final static String XMLPATH = "NewBean.xml";

     public static void main(String[] args) {
          ClassPathXmlApplicationContext appl = null;
          try {
                appl = new ClassPathXmlApplicationContext(XMLPATH);

          }finally {
                appl.close();
          }
     }

}
