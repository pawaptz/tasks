package com.ru.msk.lb.strategy;

import com.ru.msk.lb.Resource;
import com.sun.istack.internal.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class RandomSelectionStrategy implements SelectionStrategy {

    @Override
    public Resource selectNext(@NotNull List<Resource> resourceList) {
        Objects.requireNonNull(resourceList);
        if (resourceList.isEmpty()) {
            return null;
        }
        return resourceList.get(new Random().nextInt(resourceList.size()));
    }
}
