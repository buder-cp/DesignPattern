package com.example.router_core.template;

import java.util.Map;

public interface IRouteRoot {

    /**
     * @param routes input
     */
    void loadInto(Map<String, Class<? extends IRouteGroup>> routes);
}
