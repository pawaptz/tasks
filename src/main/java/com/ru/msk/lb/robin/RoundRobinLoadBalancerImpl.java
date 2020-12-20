package com.ru.msk.lb.robin;

import com.ru.msk.lb.LoadBalancer;
import com.ru.msk.lb.Resource;
import com.sun.istack.internal.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RoundRobinLoadBalancerImpl implements LoadBalancer {

    private final List<Resource> resources = new ArrayList<>();
    private int nextIndex = 0;

    @Override
    public void addResource(Resource resource) {
        resources.add(resource);
    }

    @Override
    @Nullable
    public Resource nextResource() {
        if (!checkRange(nextIndex)){
            return null;
        }
        final Resource resource = resources.get(nextIndex);
        toTheNextResource();
        return resource;
    }

    private void toTheNextResource() {
        if (++nextIndex > resources.size() - 1) {
            nextIndex = 0;
        }
    }

    private boolean checkRange(int nextResource) {
        if (resources.isEmpty()) {
            return false;
        }
        return nextResource < resources.size();
    }
}
