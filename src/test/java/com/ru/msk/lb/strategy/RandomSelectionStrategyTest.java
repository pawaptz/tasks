package com.ru.msk.lb.strategy;

import com.ru.msk.lb.DestinationResource;
import com.ru.msk.lb.Resource;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Percentage.withPercentage;
import static org.junit.jupiter.api.Assertions.*;

class RandomSelectionStrategyTest {

    @Test
    public void whenResourceListIsNullThenThrowException() {
        final SelectionStrategy lbs = new RandomSelectionStrategy();

        assertThrows(NullPointerException.class, () -> lbs.selectNext(null));
    }

    @Test
    public void whenNoResourcesThenReturnNull() {
        final SelectionStrategy lbs = new RandomSelectionStrategy();

        assertNull(lbs.selectNext(new ArrayList<>()));
    }

    @Test
    public void whenSingleResourceThenItMustBeChosen() {
        final SelectionStrategy lbs = new RandomSelectionStrategy();
        final Resource resourceExpected = new DestinationResource("http://www.yazva.ru:2020/");

        assertEquals(resourceExpected, lbs.selectNext(singletonList(resourceExpected)));
    }

    @Test
    public void whenManyResourcesThenRandomMustBeChosen() {
        final SelectionStrategy lbs = new RandomSelectionStrategy();
        final List<Resource> resList = new ArrayList<>(3);

        final DestinationResource res0 = new DestinationResource("http://www.yazva.ru:2020/");
        final DestinationResource res1 = new DestinationResource("http://www.yazva.ru:2021/");
        final DestinationResource res2 = new DestinationResource("http://www.yazva.ru:2022/");

        resList.add(res0);
        resList.add(res1);
        resList.add(res2);

        final int[] counter = new int[resList.size()];
        int rounds = Integer.MAX_VALUE / 20;
        for (int i = 0; i < rounds; i++) {
            Resource resource = lbs.selectNext(resList);
            if (resource.equals(res0)) counter[0]++;
            if (resource.equals(res1)) counter[1]++;
            if (resource.equals(res2)) counter[2]++;
        }
        assertThat((double) counter[0] / rounds).isCloseTo(0.33, withPercentage(10));
        assertThat((double) counter[1] / rounds).isCloseTo(0.33, withPercentage(10));
        assertThat((double) counter[2] / rounds).isCloseTo(0.33, withPercentage(10));
    }
}