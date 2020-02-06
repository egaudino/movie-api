package com.gaudino.movie.utils;

import com.gaudino.movie.domains.Actor;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ActorBootstrap {
    public Set<Actor> buildActorsList() {
        return Stream.of(
                new Actor("Harry Styles"),
                new Actor("Fionn Whitehead"),
                new Actor("Cillian Murphy"),
                new Actor("Tom Hardy")
        ).collect(Collectors.toSet());
    }

    public Set<Actor> buildMoreTenActorsList() {
        return Stream.of(
                new Actor("Harry Styles"),
                new Actor("Fionn Whitehead"),
                new Actor("Cillian Murphy"),
                new Actor("Tom Hardy"),
                new Actor("Yan McKellen"),
                new Actor("Scarllet Johansson"),
                new Actor("Brad Pitt"),
                new Actor("Leonardo DiCaprio"),
                new Actor("Daniel Day Lewis"),
                new Actor("Rooney Mara"),
                new Actor("Keanu Reeves")
        ).collect(Collectors.toSet());
    }
}
