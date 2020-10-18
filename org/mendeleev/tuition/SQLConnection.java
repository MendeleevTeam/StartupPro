package org.mendeleev.tuition;

import com.mysql.cj.jdbc.Driver;
import org.springframework.beans.factory.InitializingBean;

import javax.security.auth.DestroyFailedException;
import javax.security.auth.Destroyable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SQLConnection
     implements Destroyable, InitializingBean {

     private String username;
     private String passwd;
     private String IP;
     private int port;
     private Connection conn;

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

     public String getIP() {
          return IP;
     }

     public void setIP(String IP) {
          this.IP = IP;
     }

     public int getPort() {
          return port;
     }

     public void setPort(int port) {
          this.port = port;
     }

     public void execute(final String query) throws SQLException {
          
     }

     @Override
     public void afterPropertiesSet() throws Exception {
          try {
               ClassLoader.getPlatformClassLoader().loadClass("com.mysql.jdbc.Driver");
          }catch (ClassNotFoundException cls){
               System.err.println("[!]No Driver class found!");
               return;
          }
          conn = DriverManager.getConnection(IP, username, passwd);
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
