package io.gitee.message.enums;

import io.gitee.message.handler.ChannelInitializerFactory;
import io.gitee.message.handler.WebSocketInitializer;
import io.netty.channel.ChannelInitializer;

/**
 * Message服务类型
 *
 * @author Cikaros
 * @date 2022/4/8
 */
public enum MessageServer implements ChannelInitializerFactory {

    /**
     * WebSocket服务器
     */
    WEB_SOCKET {
        @Override
        public ChannelInitializer<?> getInstance() {
            return new WebSocketInitializer();
        }
    },
    /**
     * TCP服务器
     */
    TCP {
        @Override
        public ChannelInitializer<?> getInstance() {
            return null;
        }
    },
    /**
     * UDP服务器
     */
    UDP {
        @Override
        public ChannelInitializer<?> getInstance() {
            return null;
        }
    },


}
