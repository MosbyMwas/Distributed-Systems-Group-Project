import java.io.*;
import java.net.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SocketClient
{
    public static void main(String[] args) throws IOException
    {
        String hostName = "127.0.0.1";
        int portNumber = 9090; 

        try
        (
            Socket kkSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
        )
        {
            String fromServer;
            String fromUser;

            while ((fromServer = in.readLine()) != null)
            {
                JFrame f=new JFrame();  
                if (fromServer.equals("Thank you for your input. That is all I need. Bye!!!"))
                {
                    JOptionPane.showMessageDialog(f,fromServer,"Student Information",JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                    
                fromUser = JOptionPane.showInputDialog(f,fromServer);
                System.out.println(fromUser);
                if (fromUser != null)
                {
//                    System.out.println("Client: " + fromUser);   
                    out.println(fromUser);
                }
            }
        }
        catch (UnknownHostException e)
        {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        }
        catch (IOException e)
        {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}
