package io.gitee.message.handler;

import io.gitee.message.config.props.MessageProperties;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * WebSocket初始化
 *
 * @author Cikaros
 * @date 2022/4/6
 * @since v1.0
 */
@Component
@ConditionalOnProperty(name = "fly-feel.message.type", havingValue = "WEB_SOCKET", matchIfMissing = true)
public class WebSocketInitializer extends ChannelInitializer<SocketChannel> {

    private final MessageProperties config;

    public WebSocketInitializer(MessageProperties config) {
        this.config = config;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        //从Channel中获取对应的pipeline
        ChannelPipeline channelPipeline = socketChannel.pipeline();

        //添加相应的助手类与处理器
        /*
         * WebSocket基于Http，所以要有相应的Http编解码器，HttpServerCodec()
         */
        channelPipeline.addLast(new HttpServerCodec());

        //在Http中有一些数据流的传输，那么数据流有大有小，如果说有一些相应的大数据流处理的话，需要在此添加
        //ChunkedWriteHandler：为一些大数据流添加支持
        channelPipeline.addLast(new ChunkedWriteHandler());

        //UdineHttpMessage进行处理，也就是会用到request以及response
        //HttpObjectAggregator：聚合器，聚合了FullHTTPRequest、FullHTTPResponse。。。，当你不想去管一些HttpMessage的时候，直接把这个handler丢到管道中，让Netty自行处理即可
        channelPipeline.addLast(new HttpObjectAggregator(config.webSocket.maxContentLength));

        //================华丽的分割线：以上是用于支持Http协议================
        //================华丽的分割线：以下是用于支持WebSocket==================

        // /ws：一开始建立连接的时候会使用到，可自定义
        //WebSocketServerProtocolHandler：给客户端指定访问的路由（/ws），是服务器端处理的协议，当前的处理器处理一些繁重的复杂的东西，运行在一个WebSocket服务端
        //另外也会管理一些握手的动作：handshaking(close，ping，pong) ping + pong = 心跳，对于WebSocket来讲，是以frames进行传输的，不同的数据类型对应的frames也不同
        channelPipeline.addLast(new WebSocketServerProtocolHandler(config.webSocket.path));
        //=============================增加心跳支持============================

        //对客户端，如果在60秒内没有向服务端发送心跳，就主动断开
        //三个参数分别为读/写/读写的空闲，我们只针对读写空闲检测
        channelPipeline.addLast(new IdleStateHandler(config.webSocket.readerIdleTimeSeconds, config.webSocket.writerIdleTimeSeconds, config.webSocket.allIdleTimeSeconds));
        channelPipeline.addLast(new HeartBeatHandler());

        //添加自动handler，读取客户端消息并进行处理，处理完毕之后将相应信息传输给对应客户端
        channelPipeline.addLast(new ChatHandler());


    }

}