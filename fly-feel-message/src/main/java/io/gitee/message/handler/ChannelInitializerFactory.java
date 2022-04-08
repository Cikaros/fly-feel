package io.gitee.message.handler;

import io.netty.channel.ChannelInitializer;

/**
 * ChannelInitializer工厂
 *
 * @author Cikaros
 * @date 2022/4/8
 */
public interface ChannelInitializerFactory {

    ChannelInitializer<?> getInstance();
}
