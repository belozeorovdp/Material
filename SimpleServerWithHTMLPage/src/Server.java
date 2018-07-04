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
        BaseContext.setHandler(new HttpHandler() {
                    @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                String protocol = httpExchange.getProtocol();
                String requestMethod = httpExchange.getRequestMethod();
                URI requestUri = httpExchange.getRequestURI();
                Headers requestHeaders = httpExchange.getRequestHeaders();
                String htmlPage = createResponsiblePage (protocol, requestMethod, requestUri, requestHeaders);
                Headers responsibleHeaders = httpExchange.getResponseHeaders();
                responsibleHeaders.add("X-MyHeader", "1");
                responsibleHeaders.add("X-MyHeader", "2");
                responsibleHeaders.add("X-MyHeader", "3");
                httpExchange.sendResponseHeaders(200, htmlPage.length());
                OutputStream os = httpExchange.getResponseBody();
                os.write(htmlPage.getBytes(StandardCharsets.US_ASCII));
                os.close();
            }
        });
        server.start();
    }

    private static String createResponsiblePage (String protocol, String requestMethod, URI requestUri, Headers requestHeaders)
    {
        String htmlPage = "<HTML><BODY>";
        htmlPage += "<br> requestMethod: " + requestMethod + "</br>";
        htmlPage += "<br> requestUri: " + requestUri + "</br>";
        htmlPage += "<br> protocol: " + protocol + "</br>";
        for (Map.Entry<String, List<String>> header: requestHeaders.entrySet())
        {
            String key = header.getKey();
            List<String> values = header.getValue();
            htmlPage += "<br>" +  key + ":" ;
            for (String value :values)
            {
                htmlPage +=  value + ", ";
            }
            htmlPage += "<br>";
        }
        htmlPage += "</BODY></HTML>";
        return htmlPage;
    }
}
