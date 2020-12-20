package com.ru.msk;

import com.ru.msk.lb.DestinationResource;
import com.ru.msk.lb.LoadBalancer;
import com.ru.msk.lb.robin.RoundRobinLoadBalancerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoundRobinLoadBalancerImplTest {

    @Test
    public void whenNoResourcesThenReturnNull() {
        final LoadBalancer lb = new RoundRobinLoadBalancerImpl();

        Assertions.assertNull(lb.nextResource());
    }

    @Test
    public void whenResourcesAreAvailableThenReturnNext() {
        final LoadBalancer lb = new RoundRobinLoadBalancerImpl();
        final DestinationResource res1 = new DestinationResource("http://bla.com:8080/");
        final DestinationResource res2 = new DestinationResource("http://bla.com:8081/");
        final DestinationResource res3 = new DestinationResource("http://bla.com:8082/");

        lb.addResource(res1);
        lb.addResource(res2);
        lb.addResource(res3);

        assertEquals(res1, lb.nextResource());
        assertEquals(res2, lb.nextResource());
        assertEquals(res3, lb.nextResource());
    }

    @Test
    public void whenSingleResourcesIsAvailableThenReturnIt() {
        final LoadBalancer lb = new RoundRobinLoadBalancerImpl();
        final DestinationResource res1 = new DestinationResource("http://bla.com:8080/");
        lb.addResource(res1);

        assertEquals(res1, lb.nextResource());
        assertEquals(res1, lb.nextResource());
    }

    @Test
    public void whenResourcesAreAvailableAndNextConsumedThenReturnFirst() {
        final LoadBalancer lb = new RoundRobinLoadBalancerImpl();
        final DestinationResource res1 = new DestinationResource("http://bla.com:8080/");
        final DestinationResource res2 = new DestinationResource("http://bla.com:8081/");

        lb.addResource(res1);
        lb.addResource(res2);

        assertEquals(res1, lb.nextResource());
        assertEquals(res2, lb.nextResource());
        assertEquals(res1, lb.nextResource());
    }

    @Test
    public void whenAddNewResourceThenReturnIt() {
        final LoadBalancer lb = new RoundRobinLoadBalancerImpl();
        final DestinationResource res1 = new DestinationResource("http://bla.com:8080/");
        final DestinationResource res2 = new DestinationResource("http://bla.com:8081/");
        final DestinationResource res3 = new DestinationResource("http://bla.com:8082/");

        lb.addResource(res1);
        lb.addResource(res2);

        lb.nextResource();
        lb.nextResource();
        assertEquals(res1, lb.nextResource());

        lb.addResource(res3);
        lb.nextResource();
        assertEquals(res3, lb.nextResource());
    }
}