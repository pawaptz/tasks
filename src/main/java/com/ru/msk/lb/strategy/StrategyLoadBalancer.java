package com.ru.msk.lb.strategy;

import com.sun.istack.internal.Nullable;
import com.ru.msk.lb.LoadBalancer;
import com.ru.msk.lb.Resource;

import java.util.ArrayList;
import java.util.List;

public class StrategyLoadBalancer implements LoadBalancer {

    private final List<Resource> resourceList = new ArrayList<>();
    private final SelectionStrategy selectionStrategy;

    public StrategyLoadBalancer(SelectionStrategy selectionStrategy) {
        this.selectionStrategy = selectionStrategy;
    }

    @Override
    public void addResource(Resource resource) {
        resourceList.add(resource);
    }

    @Override
    @Nullable
    public Resource nextResource() {
        return selectionStrategy.selectNext(resourceList);
    }
}
