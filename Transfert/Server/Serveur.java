import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Serveur{
	  static ArrayList<Fichier> myFiles = new ArrayList<>();
    
    public static void main (String[] args) throws IOException {
          int idF = 0;
          JFrame frame = new JFrame();
          JPanel panel = new JPanel();
          JLabel jlTitle = new JLabel("wittCode's File Receiver");
          JScrollPane jScrollPane = new JScrollPane(panel);
          ServerSocket serverSocket = new ServerSocket( 1234);
		        frame.setSize(500, 500);
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame.add(jlTitle);
		        frame.add(jScrollPane);
		        frame.setVisible(true);


        while (true)
        {
             try{
                    Socket socket = serverSocket.accept();
                    DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                    int taille = dataInputStream.readInt();
                    if (taille > 0)
                    {
	                    byte[] b = new byte[taille];
	                    dataInputStream.readFully(b,0, b.length);
	                    String nom = new String(b);
	                    int taille2 = dataInputStream.readInt();

	                    if (taille2 >0)
	                    {
	                        byte[] fileContentBytes = new byte[taille2];
	                        dataInputStream.readFully(fileContentBytes, 0, taille2);
	                        JPanel panel2 = new JPanel();
	                        JLabel label = new JLabel(nom);
	
	                        if(getFileExtension(nom).equalsIgnoreCase("TXT"))
	                        {
	                            panel2.setName(String.valueOf(idF));
	                            panel2.addMouseListener(getMyMouseListener());
	                            panel2.add(label);
	                            panel.add(panel2); 
	                            frame.validate(); 
	                        }
	                        else
	                        {
	                        	panel2.setName(String.valueOf(idF));
	                        	panel2.addMouseListener(getMyMouseListener());
	                        	panel2.add(label);
	                            panel.add(panel2);
	                            frame.validate();
	                        }
	
	                         myFiles.add(new Fichier(idF, nom,fileContentBytes, getFileExtension(nom)));
	                         idF ++;
	                    }
               }
           }
              catch (IOException error) 
              {
                 error.printStackTrace();
              }
     }

}

    public  static MouseListener getMyMouseListener() 
    {

        return new MouseListener() 
        {
            public void mouseClicked(MouseEvent e)
            {
            	JPanel jPanel = (JPanel) e.getSource();
            	int iD= Integer.parseInt(jPanel.getName());

                 for(Fichier myFile: myFiles)
                 {
                    if (myFile.getId() == iD)
                    {
                        JFrame jfPreview = createFrame(myFile.getName(), myFile.getData(), myFile.getFileExtension());
                        jfPreview.setVisible(true);
                    }
                 }
            } 


            public void mousePressed(MouseEvent e) {}

            public void mouseReleased(MouseEvent e){}
            
            public void mouseEntered(MouseEvent e){}

            public void mouseExited(MouseEvent e){}
        }; 

    }

    public static JFrame createFrame(String fileName, byte[] fileData, String fileExtension) {
    	JFrame frame = new JFrame("wittCode's File Downloader");
    	JPanel panel = new JPanel();
    	JButton button1 = new JButton("yes");
    	JButton button2 = new JButton("No");
    	JLabel label = new JLabel("Confirmez si vous voulez vraiment le telecharger ou l enregistrer" + fileName );
    	JLabel labell = new JLabel();
    	JPanel panell = new JPanel();
	        frame.setSize(400, 400);
	        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	        panell.add(button1);
	        panell.add(button2);
	        panel.add(labell);
	        panel.add(label);
	        panel.add(panell);
	        frame.add(panel);


	        if(fileExtension.equalsIgnoreCase("PNG"))
	        {
	        	label.setText("<html>" + "</html>");
	        }
	        else
	        {
	        	label.setIcon(new ImageIcon(fileData));
	        }

        button1.addActionListener(new ActionListener()
        {
			public void actionPerformed(ActionEvent e) 
			{
			                File file = new File(fileName);
                            try
			                {
			                    FileOutputStream fileOutputStream = new FileOutputStream(file);
			
			                     fileOutputStream.write(fileData);
			                     fileOutputStream.close();
			                     frame.dispose();
			                } 
			                catch(IOException error)
			                {
			                    error.printStackTrace();
			                }
		  }
        });
      button2.addActionListener(new ActionListener()
      {
           public void actionPerformed(ActionEvent e) 
            {
            	frame.dispose();
            }
      });
             return frame;

    }

    public static String getFileExtension(String fileName)
    {
    	int i = fileName.lastIndexOf('.');
        if (i > 0)
        {
            return  fileName.substring(i + 1);
        } 
        else
        {
            return "No extension found.";
        }
    }
}