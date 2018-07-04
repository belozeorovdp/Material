package nio_p2p.channel_handlers;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class WebSocketChannelInitializer extends ChannelInitializer {

    private ChannelGroup channelGroup;

    public WebSocketChannelInitializer(ChannelGroup channelGroup){
        this.channelGroup=channelGroup;
    }
    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline().addLast("httpServerCodec", new HttpServerCodec());
        channel.pipeline().addLast(new HttpServerHandler());
        channel.pipeline().addLast(new WebSocketChannelInboundHandler(channelGroup));
    }
}
