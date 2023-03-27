package com.seven.gateway.service;

import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.List;

public interface RouteService {
    /**
     * 更新路由配置
     *
     * @param routeDefinitionList
     */
    void update(List<RouteDefinition> routeDefinitionList);

    /**
     * 添加路由配置
     *
     * @param routeDefinitionList
     */
    void add(List<RouteDefinition> routeDefinitionList);
}