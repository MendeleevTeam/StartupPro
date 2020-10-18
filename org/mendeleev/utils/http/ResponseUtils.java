package org.mendeleev.utils.http;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResponseUtils {

     public final static String OK_STATUE = "200 OK";
     public final static String NOT_FOUND_STATUE = "404 NOT FOUND";
     public final static String FORBIDDEN_STATUE = "403 FORBIDDEN";

     public static String currentDate(){
          SimpleDateFormat d = new SimpleDateFormat();
          return d.format(new Date());
     }

     public static String getMIME(File f) throws IOException {
          Path p = Paths.get(f.toURI());
          return Files.probeContentType(p);
     }

}
