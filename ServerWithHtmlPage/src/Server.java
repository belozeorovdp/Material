import com.sun.net.httpserver.*;
import com.sun.net.httpserver.spi.HttpServerProvider;
import sun.net.httpserver.DefaultHttpServerProvider;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class Server {
    public static void main(String[] args) throws IOException
    {
        final int backlog = 64;
        final InetSocketAddress serverAddress = new InetSocketAddress(80);
        HttpServerProvider provider = DefaultHttpServerProvider.provider();
        HttpServer server = provider.createHttpServer(serverAddress, backlog);
        HttpContext BaseContext = server.createContext("/");
        String stringPage =
                "<HTML><BODY> " +
                "<P><A HREF=\"/a.do\">a.do</A></P>" +
                "<P><A HREF=\"/b.do\">b.do</A></P>" +
                "</BODY></HTML>";
        BaseContext.setHandler(new PageHttpHandler(stringPage));

        HttpContext aContext = server.createContext("/a.do");
        stringPage =
                "<HTML><BODY> " +
                "<P><A HREF=\"/b.do\">b.do</A></P>" +
                "</BODY></HTML>";
        aContext.setHandler(new PageHttpHandler(stringPage));

        HttpContext bContext = server.createContext("/b.do");
        stringPage =
                "<HTML><BODY> " +
                "<P><A HREF=\"/a.do\">a.do</A></P>" +
                "</BODY></HTML>";
        bContext.setHandler(new PageHttpHandler(stringPage));

        server.start();
    }
}
