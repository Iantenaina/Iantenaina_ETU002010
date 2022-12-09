import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public class Serveur {
 
    public static void main(InputStream in, OutputStream out){
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
   PrintWriter outp = new PrintWriter(out);
   outp.println("!§#%%123{#done#}123%%#§!");
   outp.flush();
  } catch (IOException e) {
   e.printStackTrace();
  }
    }
    
    private static String GetString(byte[] bytes){
        return new String(bytes);
    }
    
    private static boolean isEndReached(byte[] buf){
     if(GetString(buf).contains("!§#%%123{#done#}123%%#§!")){
      return true;
     }
     return false;
    }
    
}