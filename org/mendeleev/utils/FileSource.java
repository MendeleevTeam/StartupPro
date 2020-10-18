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
     public void operate(HttpServletResponse resp) {
          BufferedOutputStream bo = null;
          try {
               bo = new BufferedOutputStream(resp.getOutputStream());
               byte[] buffer = new byte[1024];
               int read = 0;
               while(read!=-1){
                    read = is.read(buffer);
                    bo.write(buffer,0,read);
                    System.out.print(new String(buffer));
               }
               is.reset();
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
               is.mark(Integer.MAX_VALUE);
          }catch (IOException i){
               System.err.println(i);
               i.printStackTrace(errorLogger);
          }
     }
}
