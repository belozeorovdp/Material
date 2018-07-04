import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.Callable;

import static java.nio.charset.StandardCharsets.US_ASCII;

public class HttpHandler implements Callable<Void>
{
    private final Socket socket;

    public HttpHandler(Socket socket)
    {
       this.socket = socket;
    }

    @Override
    public Void call() throws Exception
    {

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
        return null;
    }

}
