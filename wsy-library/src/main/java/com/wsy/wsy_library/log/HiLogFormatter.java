package com.wsy.wsy_library.log;

/**
 * 日志格式化接口
 * @param <T>
 */
public interface HiLogFormatter<T> {

    String format(T data);
}