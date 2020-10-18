package org.mendeleev.utils;

import org.springframework.beans.factory.InitializingBean;

import javax.security.auth.DestroyFailedException;
import javax.security.auth.Destroyable;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public abstract class Source
     implements Destroyable, InitializingBean {

     protected String sourcePath;
     protected String logPath;
     protected PrintWriter errorLogger;

     @Override
     public void afterPropertiesSet() throws Exception {
          File f = new File(logPath);
          errorLogger = new PrintWriter(new FileWriter(f));
     }

     @Override
     public void destroy() throws DestroyFailedException {
          close(errorLogger);
     }

     public String getLogPath() {
          return logPath;
     }

     public void setLogPath(String logPath) {
          this.logPath = logPath;
     }

     public String getSourcePath() {
          return sourcePath;
     }

     public void setSourcePath(String sourcePath) {
          this.sourcePath = sourcePath;
     }

     protected abstract void operate(HttpServletResponse resp,String statue);

     protected void close(Closeable...c){
          for (Closeable each:
               c) {
               if(c!=null) {
                    try {
                         each.close();
                    } catch (IOException e) {
                         e.printStackTrace(errorLogger);
                    }
               }
          }
     }

}

