package org.com.qsqLt.common;

public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurId(Long id){
        threadLocal.set(id);
    }
    public static Long getCurId(){
        return threadLocal.get();
    }
}
