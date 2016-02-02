package com.zsx.rxjavatest.ui.layout;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2016/2/2.
 */
public class ViewLayerProxy {

    public static Object createProxy(ViewLayer viewLayer) {
        Class cls = viewLayer.getClass();
        InvocationHandler invocationHandler = new ViewLayerHandler(viewLayer);
        return Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), invocationHandler);
    }

}
