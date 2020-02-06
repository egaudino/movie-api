package com.gaudino.movie.gateways;

import com.gaudino.movie.domains.Censorship;
import com.gaudino.movie.domains.Movie;
import com.gaudino.movie.exceptions.MovieNotFoundException;
import com.gaudino.movie.gateways.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Component
@RequiredArgsConstructor
public class MovieGatewayMongoImpl implements MovieGateway {

    private final MovieRepository movieRepository;

    @Override
    public Movie getMovieByName(String name) {
        return Optional.ofNullable(movieRepository.findByName(name)).orElseThrow(() -> new MovieNotFoundException(name));
    }

    @Override
    public List<Movie> getMoviesByCensorshipLevel(Censorship censorshipLevel) {
        return Optional.ofNullable(movieRepository.findByCensorship(censorshipLevel)).orElse(emptyList());
    }
}
