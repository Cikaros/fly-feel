package io.gitee.message.handler;

import io.gitee.define.service.ILogger;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * 用于检测channel心跳的handler
 *
 * @author Cikaros
 * @date 2022/4/6
 * @since v1.0
 */
@Component
@ConditionalOnProperty(name = "fly-feel.message.type", havingValue = "WEB_SOCKET", matchIfMissing = true)
public class HeartBeatHandler extends ChannelInboundHandlerAdapter implements ILogger {



    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {

        //判断evt是否是IdleStateEvent(用于触发用户事件，包含读空闲/写空闲/读写空闲)
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if (idleStateEvent.state() == IdleState.READER_IDLE) {
                log.debug("进入读空闲...");
            } else if (idleStateEvent.state() == IdleState.WRITER_IDLE) {
                log.debug("进入写空闲...");
            } else if (idleStateEvent.state() == IdleState.ALL_IDLE) {
                log.debug("进入读写空闲...");
                Channel channel = ctx.channel();
                //关闭无用channel，避免浪费资源
                channel.close();
            }
        }
    }
}