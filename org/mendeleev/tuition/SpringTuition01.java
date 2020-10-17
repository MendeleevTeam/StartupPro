package org.mendeleev.tuition;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTuition01 {

     public static void main(String[] args) {
          AbstractApplicationContext context = new ClassPathXmlApplicationContext("NewBean.xml");
          Object obj = context.getBean("MyBean01");
          JavaBean01 bean = (JavaBean01)obj;
          System.out.println(bean);
          context.close();
     }

}
