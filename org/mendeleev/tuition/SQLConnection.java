package org.mendeleev.tuition;

import com.mysql.cj.jdbc.Driver;
import org.springframework.beans.factory.InitializingBean;

import javax.security.auth.DestroyFailedException;
import javax.security.auth.Destroyable;
import java.sql.*;
import java.util.Map;
import java.util.Properties;


public class SQLConnection
     implements Destroyable, InitializingBean {

     private String username;
     private String passwd;
     private String url;
     private String database;
     private int port;
     private Connection conn;

     public String getDatabase() {
          return database;
     }

     public void setDatabase(String database) {
          this.database = database;
     }

     public String getUsername() {
          return username;
     }

     public void setUsername(String username) {
          this.username = username;
     }

     public String getPasswd() {
          return passwd;
     }

     public void setPasswd(String passwd) {
          this.passwd = passwd;
     }

     public String geturl() {
          return url;
     }

     public void seturl(String url) {
          this.url = url;
     }

     public int getPort() {
          return port;
     }

     public void setPort(int port) {
          this.port = port;
     }

     public Statement execute(final String query) throws SQLException {
          Statement t = conn.createStatement();
          t.execute(query);
          return t;
     }

     public void execute(final String query, Map<Object, Integer> mp) throws SQLException {
          PreparedStatement prep = conn.prepareCall(query);
          int index = 0;
          for(Map.Entry<Object,Integer> ent : mp.entrySet()){
               prep.setObject(index,ent.getKey(),ent.getValue());
               index++;
          }
          prep.executeUpdate();
          prep.close();
     }

     @Override
     public void afterPropertiesSet() throws Exception {
          try {
               Class.forName("com.mysql.cj.jdbc.Driver");
          }catch (ClassNotFoundException cls){
               System.err.println("[!]No Driver class found!");
               return;
          }
          conn = DriverManager.getConnection(url+":"+port+"/"+database, username, passwd);
          System.out.printf("Connection established -> %s\r\n",conn.toString());
     }

     @Override
     public void destroy() throws DestroyFailedException {
          try {
               conn.close();
          } catch (SQLException throwables) {
               System.err.println(throwables);
          }
     }
}
