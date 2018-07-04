package nio_p2p.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultEventExecutor;
import nio_p2p.Utils.HashWork;
import nio_p2p.Utils.NetworkEnvironment;
import nio_p2p.channel_handlers.CommonChannelInitializer;
import nio_p2p.client.Client;
import nio_p2p.webserver.WebSocketServer;

import java.net.InetAddress;
import java.util.logging.Logger;

public class Server {

    private int port;
    private Channel listenerChannel;
    private Logger logger;
    private ChannelGroup channelGroup;
    private ChannelGroup webChanelGroup;
    private Client client;
    private WebSocketServer webSocketServer;
    private String myHash;
    private InetAddress localAddress;
    private String localMacAddress;
    private InetAddress externalAddress;

    public Server() {
        this(16161);
    }

    public Server(int port) {
        this.port = port;
        this.logger = Logger.getLogger(Server.class.getName());
        this.channelGroup = new DefaultChannelGroup(new DefaultEventExecutor());
        this.webChanelGroup = new DefaultChannelGroup(new DefaultEventExecutor());
    }

    public void run() throws Exception {
        getMyInfo();
        showMyInfo();
        startServer();
    }

    private void getMyInfo() {
        this.myHash = HashWork.giveMyHash();
        this.localAddress = NetworkEnvironment.getLocalAddress();
        this.localMacAddress = NetworkEnvironment.getLocalMacAddressReadable();
        this.externalAddress = NetworkEnvironment.getExternalAddress();
    }

    private void showMyInfo(){
        System.out.println("My hash: "+myHash);
        System.out.println("My local address: "+localAddress+" | mac address: "+localMacAddress);
        System.out.println("My external address: "+externalAddress);
    }

    private void startServer() throws Exception {
        logger.info("server start");
        EventLoopGroup listener = new NioEventLoopGroup();
        EventLoopGroup connections = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(listener, connections);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 128);
            serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            serverBootstrap.childHandler(new CommonChannelInitializer(channelGroup));

            listenerChannel = serverBootstrap.bind(port).sync().channel();

            client = new Client(channelGroup, connections);
            client.run();
            webSocketServer = new WebSocketServer(17171, listener, connections, channelGroup);
            webSocketServer.run();



/*            new Client(channelGroup, connections).run().connect("localhost", 16161);
            new Client(channelGroup, connections).run().connect("localhost", 16161);*/


            listenerChannel.closeFuture().sync();
        } finally {
            connections.shutdownGracefully();
            listener.shutdownGracefully();
            webSocketServer.shutDown();


        }
    }

}
