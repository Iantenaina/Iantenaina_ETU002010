import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
public class transfert{
public static void main(InputStream in, OutputStream out, boolean closeOnExit,String[]args) throws IOException {
  byte buf[] = new byte[1024];
  
  int n;
  while((n=in.read(buf))==1024){
      out.write(buf,0,n);
  }
  //et la on ajoute les bytes plus petits que 1024 ) la fin
  out.write(buf,0,n);
  if (closeOnExit) {
      in.close();
      out.close();
  }
}
}