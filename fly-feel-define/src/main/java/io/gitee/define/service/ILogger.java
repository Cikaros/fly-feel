package io.gitee.define.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志接口
 *
 * @author Cikaros
 * @date 2022/4/6
 * @since v1.0
 */
public interface ILogger {

    Logger log = LoggerFactory.getLogger(ILogger.class);

}
