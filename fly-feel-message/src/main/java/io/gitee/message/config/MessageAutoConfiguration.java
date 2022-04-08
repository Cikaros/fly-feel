package io.gitee.message.config;

import io.gitee.define.service.ILogger;
import io.gitee.message.config.props.MessageProperties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Message自动装配
 *
 * @author Cikaros
 * @date 2022/4/6
 * @since v1.0
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(MessageProperties.class)
public class MessageAutoConfiguration implements CommandLineRunner, ILogger {

    private MessageProperties config;

    @Autowired
    public void setConfig(MessageProperties config) {
        this.config = config;
    }

    @Override
    public void run(String... args) throws Exception {
        //定义线程组
        EventLoopGroup mainGroup = new NioEventLoopGroup();
        EventLoopGroup subGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(mainGroup, subGroup)
                    //channel类型
                    .channel(NioServerSocketChannel.class)
                    //针对subGroup做的子处理器，childHandler针对WebSocket的初始化器
                    .childHandler(config.type.getInstance());
            //绑定端口并以同步方式进行使用
            ChannelFuture channelFuture = server.bind(config.port).sync();
            //针对channelFuture，进行相应的监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            //针对两个group进行优雅地关闭
            mainGroup.shutdownGracefully();
            subGroup.shutdownGracefully();
        }
    }
}
