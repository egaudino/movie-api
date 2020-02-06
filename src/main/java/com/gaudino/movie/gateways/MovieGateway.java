package com.gaudino.movie.gateways;

import com.gaudino.movie.domains.Censorship;
import com.gaudino.movie.domains.Movie;
import com.gaudino.movie.exceptions.MovieNotFoundException;

import java.util.List;

public interface MovieGateway {

    Movie getMovieByName(String name) throws MovieNotFoundException;
    List<Movie> getMoviesByCensorshipLevel(Censorship censorshipLevel);
}
