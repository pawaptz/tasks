package com.ru.msk.lb;

/**
 * add random LB. + testing (how to test)
 * + TDD practice
 * + add strategy pattern (check what is easier to test)
 */
public interface LoadBalancer {

    void addResource(Resource resource);

    Resource nextResource();
}
