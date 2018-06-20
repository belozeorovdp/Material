package network;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;

public class TCPConnection
{
    private final Socket socket;
    private final Thread rxThread;
    private final TCPConnectionListener evenListener;
    private final BufferedReader in;
    private final BufferedWriter out;

    public  TCPConnection(TCPConnectionListener evenListener, String ipAddress, int port) throws IOException
    {
        this(evenListener, new Socket(ipAddress, port));
    }



    public  TCPConnection(TCPConnectionListener evenListener, Socket socket) throws IOException
    {
        this.evenListener = evenListener;
        this.socket = socket;
        in  = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("UTF-8")));
        rxThread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    evenListener.onConnectionReady(TCPConnection.this);
                    while(!rxThread.isInterrupted())
                    {
                        String msg = in.readLine();
                        evenListener.oneReceiverString(TCPConnection.this,msg);
                    }

                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    evenListener.onDisconnect(TCPConnection.this);
                }

            }
        });
        rxThread.start();
    }

    public synchronized void sendString(String value)
    {
        try {
            out.write(value +"\r\n");
            out.flush();
        } catch (IOException e) {
            evenListener.onException(TCPConnection.this, e);
            e.printStackTrace();
            disConnect();
        }
    }

    public synchronized void disConnect()
    {
        rxThread.interrupt();
        try
        {
            socket.close();
        }
        catch (IOException e)
        {
            evenListener.onException(TCPConnection.this, e);
            e.printStackTrace();
        }
    }

    @Override
    public String toString()
    {
        return "TCPConnection:" + socket.getInetAddress() + ":" + socket.getPort();
    }
}
