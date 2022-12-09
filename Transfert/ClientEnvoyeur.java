import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public class ClientEnvoyeur {
    public static void transfert(InputStream in, OutputStream out) throws IOException {
        byte buf[] = new byte[1024];
        
        int n;
        while((n=in.read(buf))!=-1){
            out.write(buf,0,n);
        }
        PrintWriter outp = new PrintWriter(out);
  outp.println("!§#%%123{#done#}123%%#§!");
  outp.flush();
    }
}
