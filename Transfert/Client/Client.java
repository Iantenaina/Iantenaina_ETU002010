import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;



public class Client{

public static void main (String[] args) {

    final File[] fileToSend = new File[1];
    JFrame frame = new JFrame () ;
	    JLabel label = new JLabel();
	    JPanel panel = new JPanel();
		    JButton Mandefa = new JButton("Envoye");
		    JButton Misafidy = new JButton("Choix");
			    panel.add(Mandefa);
			    panel.add(Misafidy);
			        frame.add(label);
				    frame.setSize (500, 500);
				    frame.add(panel);
				    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				    frame.setVisible(true);
 

    Misafidy.addActionListener(new ActionListener()
   {
        public void actionPerformed(ActionEvent e)
        {
            JFileChooser jFileChooser = new JFileChooser();
	           if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
	           {
	                 fileToSend[0] = jFileChooser.getSelectedFile(); 
                       //jlFileName.setText(fileToSend[0].getName());
	           }
        }
    });
 
    Mandefa.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            if (fileToSend[0] == null)
            {
                label.setText("Choisissez avantd envoyer");
            } 
            else
            {
                try{
                    FileInputStream fileInputStream = new FileInputStream(fileToSend[0].getAbsolutePath());
                    Socket socket = new Socket ("localhost",1234);
                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

                    String nom = fileToSend[0].getName();
                    byte[] fileNameBytes = nom.getBytes();
                    byte[] fileContentBytes = new byte[(int)fileToSend[0].length()];
                    fileInputStream.read(fileContentBytes);

                    dataOutputStream.writeInt(fileNameBytes.length); 
                    dataOutputStream.write(fileNameBytes);
                    dataOutputStream.writeInt(fileContentBytes.length);
                    dataOutputStream.write(fileContentBytes);
                 }
                catch (IOException error)
                {
                    error.printStackTrace();
                }
            }
        }
    });




    }
    
}
  