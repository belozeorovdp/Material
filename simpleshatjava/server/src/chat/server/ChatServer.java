package chat.server;

import network.TCPConnection;
import network.TCPConnectionListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;


public class ChatServer implements TCPConnectionListener
{
    public static void main(String[] args) {
        new ChatServer();
    }

    private final ArrayList<TCPConnection> connections = new ArrayList<>();


    private ChatServer()
    {
        System.out.println("Server running.");
        try(ServerSocket serverSocket = new ServerSocket(8888))
        {

            while (true)
            {
                try
                {
                    new TCPConnection(this, serverSocket.accept());
                }
                catch(IOException e)
                {
                    System.out.println("TCPConnection exception: " + e);
                }
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }

    @Override
    public synchronized void onConnectionReady(TCPConnection tcpConnection) {
        connections.add(tcpConnection);
        sendAllToConnections("Client connection: " + tcpConnection);
    }

    @Override
    public synchronized void oneReceiverString(TCPConnection tcpConnection, String value) {
        sendAllToConnections(value);
    }

    @Override
    public synchronized void onDisconnect(TCPConnection tcpConnection) {
        connections.remove(tcpConnection);
        sendAllToConnections("Client disconnection: " + tcpConnection);
    }

    @Override
    public synchronized void onException(TCPConnection tcpConnection, Exception e) {
        System.out.println("TCPConnection exception: " + e);
    }
    private void sendAllToConnections(String value)
    {
        System.out.println(value);
        final int count =  connections.size();
        for (int i = 0; i < count; i++)
        {
            connections.get(i).sendString(value);
        }
    }
}
