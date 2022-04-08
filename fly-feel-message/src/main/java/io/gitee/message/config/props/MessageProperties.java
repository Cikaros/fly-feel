package io.gitee.message.config.props;

import io.gitee.message.enums.MessageServer;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Message配置
 *
 * @author Cikaros
 * @date 2022/4/8
 */
@ConfigurationProperties("fly-feel.message")
public class MessageProperties {

    /**
     * 服务器类型
     */
    public MessageServer type;

    /**
     * 监听端口
     */
    public Integer port = 10086;


}
