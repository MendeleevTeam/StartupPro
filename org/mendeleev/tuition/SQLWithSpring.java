package org.mendeleev.tuition;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLWithSpring {

     final static String XMLPATH = "NewBean.xml";
     final static String QUERY_SELECT = "SELECT * FROM account";

     public static void main(String[] args) {
          ClassPathXmlApplicationContext appl = null;
          ResultSet rs = null;
          Statement t = null;
          try {
               appl = new ClassPathXmlApplicationContext(XMLPATH);
               SQLConnection sql = appl.getBean("SQLConn",SQLConnection.class);
               t = sql.execute(QUERY_SELECT);
               rs = t.getResultSet();
               while(rs.next()){
                    System.out.printf("%s %s\r\n",rs.getString(1),rs.getString(2));
               }
          }catch (SQLException sl){
               System.err.println(sl);
               sl.printStackTrace();
          }finally {
               if(appl!=null) appl.close();
               if(rs!=null) {
                    try {
                         rs.close();
                    } catch (SQLException throwables) {
                         throwables.printStackTrace();
                    }
               }
               if(t!=null) {
                    try {
                         t.close();
                    } catch (SQLException throwables) {
                         throwables.printStackTrace();
                    }
               }
          }
     }

}
