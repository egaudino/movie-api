package com.gaudino.movie.usecases;

import com.gaudino.movie.domains.Movie;
import com.gaudino.movie.exceptions.MovieBusinessException;
import com.gaudino.movie.gateways.repositories.MovieRepository;
import com.gaudino.movie.utils.ActorBootstrap;
import com.gaudino.movie.utils.MovieBootstrap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;

import static java.util.Collections.emptySet;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateMovieTest {

    @InjectMocks
    CreateMovie createMovie;

    @Mock
    MovieRepository movieRepository;

    @Mock
    MessageSource messageSource;

    @Spy
    MovieBootstrap movieBootstrap;

    @Spy
    ActorBootstrap actorBootstrap;

    Movie movie;

    @Before
    public void init() {
        movie = movieBootstrap.buildMovieObject();

    }

    @Test
    public void shouldCreateMovie() {
        when(movieRepository.findByName(any())).thenReturn(null);
        createMovie.execute(movie);
        verify(movieRepository).save(movie);
    }

    @Test(expected = MovieBusinessException.class)
    public void shouldValidateIfListActorsAreEmpty() {
        movie.setActors(emptySet());
        when(movieRepository.findByName(any())).thenReturn(null);
        createMovie.execute(movie);
        verify(movieRepository, never()).save(movie);
    }

    @Test(expected = MovieBusinessException.class)
    public void shouldValidateIfListContainsMoreThanTenActors() {
        movie.setActors(actorBootstrap.buildMoreTenActorsList());
        when(movieRepository.findByName(any())).thenReturn(null);
        createMovie.execute(movie);
        verify(movieRepository, never()).save(movie);
    }
}