package com.zsx.rxjavatest.ui.expansion;

import com.zsx.rxjavatest.ui.layout.Container;

/**
 * 扩展视图工厂类
 */
public abstract class ViewExpansionDelegateProvider {

    public abstract ViewExpansionDelegate createViewExpansionDelegate(Container container);

    public static ViewExpansionDelegateProvider DEFAULT = new ViewExpansionDelegateProvider() {
        @Override
        public ViewExpansionDelegate createViewExpansionDelegate(Container container) {
            return new DefaultViewExpansionDelegate(container);
        }
    };

}
