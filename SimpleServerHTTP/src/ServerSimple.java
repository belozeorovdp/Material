import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import static java.nio.charset.StandardCharsets.US_ASCII;


// Работает только в Mozilla/5.0. Почему?
public class ServerSimple
{
    public static void main(String[] args) throws IOException
    {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true)
        {
            System.out.println("Wait for TCP-connect ...");
            // todo: TASK FOR STUDENTS: Что имменно происходит при accept?
            // todo: TASK FOR STUDENTS: Опишите попакетно TCP handshake.
            Socket socket = serverSocket.accept();
            System.out.println("Get one!");
            try (InputStream in = socket.getInputStream();
                 OutputStream out = socket.getOutputStream())
            {
                byte[] request = HttpUtils.readRequestFully(in);
                System.out.println("------------------------");
                System.out.println(new String(request, US_ASCII));
                System.out.println("------------------------");
                byte[] response = new Date().toString().getBytes(US_ASCII);
                System.out.println("------------------------");
                out.write(response);
            }
            finally
            {
                socket.close();
            }
        }
        // todo: TASK FOR STUDENTS: Что происходит при  OS.close.
    }
}
