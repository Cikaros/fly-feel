package io.gitee.message.handler;

import io.gitee.define.service.ILogger;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * 处理消息的handler
 *
 * @author Cikaros
 * @date 2022/4/6
 * @since v1.0
 */
@Component
@ConditionalOnProperty(name = "fly-feel.message.type", havingValue = "WEB_SOCKET", matchIfMissing = true)
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> implements ILogger {

    //用于记录和管理所有客户端的channel，可以把相应的channel保存到一整个组中
    //DefaultChannelGroup：用于对应ChannelGroup，进行初始化
    private static final ChannelGroup channelClient = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
        //text()获取从客户端发送过来的字符串
        String content = msg.text();
        log.debug("客户端传输的数据：{}", content);

        //针对channel进行发送，客户端对应的是channel
        /*
         * 方式一
         */
//        for (Channel channel : channelClient) {
//            //循环对每一个channel对应输出即可（往缓冲区中写，写完之后再刷到客户端）
//            //注：writeAndFlush不可以使用String，因为传输的载体是一个TextWebSocketFrame，需要把消息通过载体再刷到客户端
//            channel.writeAndFlush(new TextWebSocketFrame("【服务器于 " + LocalDate.now() + "接收到消息：】 ，消息内容为：" + content));
//
//        }
        /*
         * 方式二
         */
        channelClient.writeAndFlush(new TextWebSocketFrame("【服务器于 " + LocalDate.now() + "接收到消息：】 ，消息内容为：" + content));

    }

    //当客户端连接服务端（或者是打开连接之后）
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        //获取客户端所对应的channel，添加到一个管理的容器中即可
        log.debug("用户连接服务器！");
        log.debug("channel的长ID：{}", ctx.channel().id().asLongText());
        log.debug("channel的短ID：{}", ctx.channel().id().asShortText());
        channelClient.add(ctx.channel());
    }

    //客户端断开
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        //实际上是多余的，只要handler被移除，client会自动的把对应的channel移除掉
        channelClient.remove(ctx.channel());
        //每一而channel都会有一个长ID与短ID
        //一开始channel就有了，系统会自动分配一串很长的字符串作为唯一的ID，如果使用asLongText()获取的ID是唯一的，asShortText()会把当前ID进行精简，精简过后可能会有重复
        log.debug("用户断开服务器！");
        log.debug("channel的长ID：{}", ctx.channel().id().asLongText());
        log.debug("channel的短ID：{}", ctx.channel().id().asShortText());
    }


}