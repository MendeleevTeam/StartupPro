package org.mendeleev.utils.http;

public class ResponseContext {

     private StringBuilder sb = new StringBuilder();

     public ResponseContext(String protocal,String statue){
          sb.append(protocal).append(" ").append(statue).append("\r\n");
          sb.append("\r\n");
     }

     public void addHeader(String key,String value){
          int keylen = key.length();
          int headoffset = sb.indexOf("\r\n");
          sb.insert(headoffset+2,key);
          headoffset += 2+keylen;
          sb.insert(headoffset,": ");
          headoffset += 2;
          sb.insert(headoffset,value+"\r\n");
     }

     public byte[] data(){
          return sb.toString().getBytes();
     }

}
