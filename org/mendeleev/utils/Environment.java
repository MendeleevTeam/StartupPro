package org.mendeleev.utils;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Environment {

     public static ClassPathXmlApplicationContext appl =
          new ClassPathXmlApplicationContext("setting.xml");

}
