package com.zsx.rxjavatest.ui.expansion;

import com.zsx.rxjavatest.ui.layout.ViewLayer;

/**
 * 扩展视图工厂类
 */
public abstract class ViewExpansionDelegateProvider {

    public abstract ViewExpansionDelegate createViewExpansionDelegate(ViewLayer viewLayer);

    public static ViewExpansionDelegateProvider DEFAULT = new ViewExpansionDelegateProvider() {
        @Override
        public ViewExpansionDelegate createViewExpansionDelegate(ViewLayer viewLayer) {
            return new DefaultViewExpansionDelegate(viewLayer);
        }
    };

}
