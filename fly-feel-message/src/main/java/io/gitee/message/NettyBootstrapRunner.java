package io.gitee.message;

import io.gitee.message.config.props.MessageProperties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

/**
 * 初始化Netty服务
 *
 * @author Cikaros
 * @date 2022/4/6
 * @since v1.0
 */
public class NettyBootstrapRunner implements CommandLineRunner, ApplicationListener<ContextClosedEvent> {

    private Channel serverChannel;

    private final MessageProperties config;

    private final ChannelInitializer<SocketChannel> channelInitializer;

    private final EventLoopGroup bossGroup = new NioEventLoopGroup();

    private final EventLoopGroup workerGroup = new NioEventLoopGroup();

    @Autowired
    public NettyBootstrapRunner(MessageProperties config, ChannelInitializer<SocketChannel> channelInitializer) {
        this.config = config;
        this.channelInitializer = channelInitializer;
    }

    public void onApplicationEvent(ContextClosedEvent event) {
        if (this.serverChannel != null) {
            this.serverChannel.close();
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(channelInitializer)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
        //绑定端口并以同步方式进行使用
        ChannelFuture channelFuture = serverBootstrap.bind(config.port).sync();
        this.serverChannel = channelFuture.channel();
    }
}