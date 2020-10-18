package org.mendeleev.utils;

import javax.security.auth.DestroyFailedException;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileSource
     extends Source{

     private BufferedInputStream is;

     @Override
     public void destroy() throws DestroyFailedException {
          super.destroy();
          close(is);
     }

     @Override
     protected void operate(HttpServletResponse resp) {
          BufferedOutputStream bo = null;
          try {
               bo = new BufferedOutputStream(resp.getOutputStream());
               byte[] buffer = new byte[1024];
               int read = 0;
               do {
                    read = is.read(buffer);
                    bo.write(buffer,0,read);
               }while(read!=0);
          } catch (FileNotFoundException e) {
               System.err.println(e);
               e.printStackTrace(errorLogger);
          } catch (IOException e) {
               System.err.println(e);
               e.printStackTrace(errorLogger);
          } finally {
               close(bo);
          }
     }

     @Override
     public void afterPropertiesSet() throws Exception {
          super.afterPropertiesSet();
          try{
               is = new BufferedInputStream(new FileInputStream(sourcePath));
          }catch (IOException i){
               System.err.println(i);
               i.printStackTrace(errorLogger);
          }
     }
}
