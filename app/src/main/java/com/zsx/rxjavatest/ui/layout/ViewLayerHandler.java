package com.zsx.rxjavatest.ui.layout;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ViewLayerHandler implements InvocationHandler {

    public Object mObject;

    public ViewLayerHandler() {

    }

    public ViewLayerHandler(Object obj) {
        mObject = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("---------before---------");
        if (((ViewLayer) mObject).isFinishing()) {
            System.out.println("view is finishing");
            return null;
        } else {
            System.out.println("view is live");
            return method.invoke(mObject, args);
        }
    }
}
