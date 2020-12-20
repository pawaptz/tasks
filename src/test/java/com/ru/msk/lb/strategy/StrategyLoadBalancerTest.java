package com.ru.msk.lb.strategy;

import com.ru.msk.lb.DestinationResource;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

class StrategyLoadBalancerTest {

    private final SelectionStrategy strategy = Mockito.mock(SelectionStrategy.class);
    private final StrategyLoadBalancer slb = new StrategyLoadBalancer(strategy);

    @Test
    public void whenNoResourcesThenReturnNull() {
        when(strategy.selectNext(Mockito.eq(Collections.emptyList()))).thenReturn(null);

        assertNull(slb.nextResource());
    }

    @Test
    public void whenResourcesAreAvailableThenReturnNext() {
        final DestinationResource res1 = new DestinationResource("http://bla.com:8080/");
        final DestinationResource res2 = new DestinationResource("http://bla.com:8081/");
        final DestinationResource res3 = new DestinationResource("http://bla.com:8082/");

        slb.addResource(res1);
        slb.addResource(res2);
        slb.addResource(res3);

        when(strategy.selectNext(Mockito.eq(Lists.newArrayList(res1, res2, res3))))
                .thenReturn(res1)
                .thenReturn(res2)
                .thenReturn(res3);

        assertEquals(res1, slb.nextResource());
        assertEquals(res2, slb.nextResource());
        assertEquals(res3, slb.nextResource());
    }

    @Test
    public void whenSingleResourcesIsAvailableThenReturnIt() {
        final DestinationResource res1 = new DestinationResource("http://bla.com:8080/");
        slb.addResource(res1);

        when(strategy.selectNext(Mockito.eq(Lists.newArrayList(res1))))
                .thenReturn(res1);

        assertEquals(res1, slb.nextResource());
        assertEquals(res1, slb.nextResource());
    }
}