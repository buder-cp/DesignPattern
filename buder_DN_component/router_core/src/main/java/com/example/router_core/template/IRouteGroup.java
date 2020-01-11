package com.example.router_core.template;



import com.example.router_annotation.model.RouteMeta;

import java.util.Map;

public interface IRouteGroup {

    void loadInto(Map<String, RouteMeta> atlas);
}
