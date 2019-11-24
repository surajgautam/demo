package com.surajgautam.demo.domain;

@FunctionalInterface
public interface Visitable<T extends Visitor> {

    void accept(T visitor);

}
