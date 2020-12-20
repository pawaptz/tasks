package com.ru.msk.lb.strategy;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import com.ru.msk.lb.Resource;

import java.util.List;

public interface SelectionStrategy {

    @Nullable
    Resource selectNext(@NotNull List<Resource> resourceList);
}
