package com.zsx.rxjavatest.ui.layout;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 */
public class ViewLayerProxy {

    /**
     * 为ViewLayer 对象创建动态代理
     *
     * @param viewLayer Activity或者Fragment的View层对象
     * @return 返回对象只能转换成该对象的接口类型
     */
    public static Object createProxy(ViewLayer viewLayer) {
        Class cls = viewLayer.getClass();
        InvocationHandler invocationHandler = new ViewLayerHandler(viewLayer);
        return Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), invocationHandler);
    }

}
