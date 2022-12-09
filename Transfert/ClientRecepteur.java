import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ClientRecepteur {
 
    public static void transfert(InputStream in, OutputStream out){
        byte buf[] = new byte[1024];
        
        int n;
        try {
         boolean cont = true;
   while(cont){
    n = in.read(buf);
       out.write(buf,0,n);
       if(isEndReached(buf)){
        cont = false;
       }
   }
  } catch (IOException e) {
   e.printStackTrace();
  }
    }
    
    static String GetString(byte[] bytes){
        return new String(bytes);
    }
    
    public static boolean isEndReached(byte[] buf){
     if(GetString(buf).contains("!§#%%123{#done#}123%%#§!")){
      return true;
     }
     return false;
    }
    
}