package nio_p2p.channel_handlers;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class CommonChannelInitializer extends ChannelInitializer {

    private ChannelGroup channelGroup;

    public CommonChannelInitializer (ChannelGroup channelGroup){
        this.channelGroup=channelGroup;
    }
    @Override
    protected void initChannel(Channel channel) throws Exception {
        //channel.pipeline().addLast("deserialization",new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
        //channel.pipeline().addLast("serialization",new ObjectEncoder());
        channel.pipeline().addLast(new InboundChannelHandler(channelGroup));
    }
}
