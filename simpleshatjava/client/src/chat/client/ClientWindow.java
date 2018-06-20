package chat.client;

import network.TCPConnection;
import network.TCPConnectionListener;
// import sun.rmi.transport.tcp.TCPConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClientWindow extends JFrame implements ActionListener, TCPConnectionListener {

    private static final String IP_ADD = /*"192.168.0.100";*/ "localhost";
    private static final int PORT = 8888;
    private static final int WIDTH = 700;
    private static final int HEIGHT = 700;



    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientWindow();
            }
        });
    }


    private final JTextArea log = new JTextArea();
    private final JTextField fieldNickName = new JTextField("Name");
    private final JTextField fieldInput = new JTextField();

    private TCPConnection connection;


    private ClientWindow()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);

        log.setEnabled(false);
        log.setLineWrap(true);
        add(log, BorderLayout.CENTER);
        fieldInput.addActionListener(this);

        add(fieldInput, BorderLayout.SOUTH);
        add(fieldNickName, BorderLayout.NORTH);

        setVisible(true);

        try
        {
            connection = new TCPConnection(this, IP_ADD, PORT);
        }
        catch (IOException e)
        {
            printMessewge("Connection exception: "+ e);
            e.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = fieldInput.getText();
        if (msg.equals("")) return;
        fieldInput.setText(null);
        connection.sendString(fieldNickName.getText() +": " + msg);
    }

    @Override
    public void onConnectionReady(network.TCPConnection tcpConnection) {
        printMessewge("Connection ready ...");
    }

    @Override
    public void oneReceiverString(network.TCPConnection tcpConnection, String value) {
        printMessewge(value);
    }

    @Override
    public void onDisconnect(network.TCPConnection tcpConnection) {
        printMessewge("Connection close ...");
    }

    @Override
    public void onException(network.TCPConnection tcpConnection, Exception e) {
        printMessewge("Connection exception: "+ e);
    }

    private synchronized void printMessewge(String msg)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                log.append(msg + "\n");
                log.setCaretPosition(log.getDocument().getLength());
            }
        });
    }

}
