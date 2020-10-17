package org.mendeleev.tuition;

import org.springframework.beans.factory.InitializingBean;

import javax.security.auth.DestroyFailedException;
import javax.security.auth.Destroyable;

public class JavaBean01
     implements InitializingBean , Destroyable {

     public int id;
     public String name;

     @Override
     public void destroy() throws DestroyFailedException {
          System.out.println("JavaBean01.destroy executed");
     }

     @Override
     public void afterPropertiesSet() throws Exception {
          System.out.println("JavaBean01.afterPropertiesSet loaded");
     }

     public int getId() {
          return id;
     }

     public void setId(int id) {
          this.id = id;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     @Override
     public String toString() {
          return "JavaBean01{" +
               "id=" + id +
               ", name='" + name + '\'' +
               '}';
     }
}

class SQLConnection {

}
