package com.sara.gateway.service.impl;

import com.sara.gateway.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class RouteServiceImpl implements RouteService, ApplicationEventPublisherAware {

    /**
     * 提供了对路由的增加删除等操作
     */
    @Resource
    private RouteDefinitionWriter routeDefinitionWriter;

    /**
     * 事件发布者
     * 是ApplicationContext的父接口之一，他的功能就是发布事件，也就是把某个事件告诉所有与这个事件相关的监听器
     */
    private ApplicationEventPublisher publisher;

    @Override
    public void update(List<RouteDefinition> routeDefinitions) {
        for (RouteDefinition definition : Objects.requireNonNull(routeDefinitions)) {
            log.info("更新路由配置项：{}", definition);
            this.routeDefinitionWriter.delete(Mono.just(definition.getId()));
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        }
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    @Override
    public void add(List<RouteDefinition> routeDefinitions) {
        for (RouteDefinition definition : Objects.requireNonNull(routeDefinitions)) {
            log.info("新增路由配置项：{}", definition);
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        }
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}