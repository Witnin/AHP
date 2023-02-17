package com.wsy.wsy_library.log;

public abstract class HiLogConfig {
    //最大长度
    static int MAX_LEN = 512;

    //懒汉模式创建单例
    static HiThreadFormatter HI_THREAD_FORMATTER = new HiThreadFormatter();
    static HiStackTraceFormatter HI_STACK_TRACE_FORMATTER = new HiStackTraceFormatter();

    //序列化
    public JsonParser injectJsonParser() {
        return null;
    }

    public interface JsonParser {
        String toJson(Object src);
    }

    public String getGlobalTag() {
        return "HiLog";
    }

    public boolean enable() {
        return true;
    }

    public boolean includeThread() {
        return false;
    }

    public int stackTraceDepth() {
        return 5;
    }

    public HiLogPrinter[] printers() {
        return null;
    }

}
