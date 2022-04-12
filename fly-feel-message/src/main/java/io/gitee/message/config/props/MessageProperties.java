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
    public MessageServer type = MessageServer.WEB_SOCKET;

    /**
     * 监听端口
     */
    public Integer port = 10086;

    /**
     * WebSocket相关配置
     */
    public WebSocketProperties webSocket = new WebSocketProperties();

    /**
     * WebSocket相关配置
     *
     * @author Cikaros
     * @date 2022/4/8
     */
    public static class WebSocketProperties {

        /**
         * HttpContent最大长度
         */
        public Integer maxContentLength = 131072;

        /**
         * 请求地址
         */
        public String path = "/ws";

        /**
         * 读取空闲时间
         */
        public Integer readerIdleTimeSeconds = 2;

        /**
         * 写出空闲时间
         */
        public Integer writerIdleTimeSeconds = 4;

        /**
         * 读写空闲时间
         */
        public Integer allIdleTimeSeconds = 60;

        public void setMaxContentLength(Integer maxContentLength) {
            this.maxContentLength = maxContentLength;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public void setReaderIdleTimeSeconds(Integer readerIdleTimeSeconds) {
            this.readerIdleTimeSeconds = readerIdleTimeSeconds;
        }

        public void setWriterIdleTimeSeconds(Integer writerIdleTimeSeconds) {
            this.writerIdleTimeSeconds = writerIdleTimeSeconds;
        }

        public void setAllIdleTimeSeconds(Integer allIdleTimeSeconds) {
            this.allIdleTimeSeconds = allIdleTimeSeconds;
        }
    }

    public void setType(MessageServer type) {
        this.type = type;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public void setWebSocket(WebSocketProperties webSocket) {
        this.webSocket = webSocket;
    }
}
