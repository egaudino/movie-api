package com.gaudino.movie.utils;

import com.gaudino.movie.domains.Censorship;
import com.gaudino.movie.domains.Movie;

import java.time.LocalDate;

public class MovieBootstrap {

    public Movie buildMovieObject() {
        Movie movie = new Movie();
        ActorBootstrap actorBootstrap = new ActorBootstrap();

        movie.setActors(actorBootstrap.buildActorsList());
        movie.setCensorship(Censorship.NOT_CENSORED);
        movie.setDirector("Cristopher Nolan");
        movie.setName("Dunkirk");
        movie.setReleaseDate(LocalDate.of(2017, 7, 27));


        return movie;
    }

}
