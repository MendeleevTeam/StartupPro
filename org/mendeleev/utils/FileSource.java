package org.mendeleev.utils;

import org.mendeleev.utils.http.ResponseContext;
import org.mendeleev.utils.http.ResponseUtils;

import javax.security.auth.DestroyFailedException;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSource
     extends Source{

     private BufferedInputStream is;
     private File f;

     @Override
     public void destroy() throws DestroyFailedException {
          super.destroy();
          close(is);
     }

     public long getSize(){
          return f.getTotalSpace();
     }

     @Override
     public void operate(HttpServletResponse resp,String statue) {
          BufferedOutputStream bo = null;
          try {

               ResponseContext con = new ResponseContext("HTTP/2.0",statue);
               con.addHeader("Server","NGINX");
               con.addHeader("Date", ResponseUtils.currentDate());
               con.addHeader("Content-length",String.valueOf(getSize()));
               con.addHeader("Content-type", ResponseUtils.getMIME(f));
               bo = new BufferedOutputStream(resp.getOutputStream());
               bo.write(con.data());
               byte[] buffer = new byte[1024];
               int read = 0;
               while(read!=-1){
                    read = is.read(buffer);
                    bo.write(buffer,0,read);
               }
               is.reset();
          } catch (FileNotFoundException e) {
               errorLogger.println(e);
               e.printStackTrace(errorLogger);
          } catch (IOException e) {
               errorLogger.println(e);
               e.printStackTrace(errorLogger);
          } finally {
               close(bo);
          }
     }

     @Override
     public void afterPropertiesSet() throws Exception {
          super.afterPropertiesSet();
          try{
               f = new File(sourcePath);
               is = new BufferedInputStream(new FileInputStream(f));
               is.mark(Integer.MAX_VALUE);
          }catch (IOException i){
               errorLogger.println(i);
               i.printStackTrace(errorLogger);
          }
     }
}
