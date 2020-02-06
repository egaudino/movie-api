package com.gaudino.movie.usecases;

import com.gaudino.movie.domains.Actor;
import com.gaudino.movie.domains.Movie;
import com.gaudino.movie.exceptions.MovieBusinessException;
import com.gaudino.movie.gateways.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Locale;
import java.util.Set;

import static java.util.Optional.ofNullable;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateMovie {

    private static final String MOVIE_ALREADY_EXIST = "movie.already.exist";
    private static final String ACTORS_LIST_EXCEEDED = "actors.list.exceeded";
    private static final String ACTORS_LIST_EMPTY = "actors.list.empty";
    private static final int MAX_ACTORS = 10;
    private final MovieRepository movieRepository;
    private final MessageSource messageSource;

    public Movie execute(final Movie movie) {

        ofNullable(movieRepository.findByName(movie.getName()))
                .ifPresent(this::movieAlreadyExists);

        if (actorsListIsEmpty(movie.getActors())) {
            throw new MovieBusinessException(
                    messageSource.getMessage(
                            ACTORS_LIST_EMPTY,
                            null,
                            Locale.ENGLISH
                    )
            );
        }

        if (containsMoreThanTenActors(movie.getActors())) {
            throw new MovieBusinessException(
                    messageSource.getMessage(
                            ACTORS_LIST_EXCEEDED,
                            null,
                            Locale.ENGLISH)
            );
        }

        log.info("Saving movie: {}", movie.getName());
        return movieRepository.save(movie);
    }

    private void movieAlreadyExists(final Movie movie) {
        throw new MovieBusinessException(
                messageSource.getMessage(
                        MOVIE_ALREADY_EXIST,
                        new Object[]{movie.getName()},
                        Locale.ENGLISH
                ));
    }

    private boolean containsMoreThanTenActors(final Set<Actor> actors) {
        return actors.size() > MAX_ACTORS;
    }

    private boolean actorsListIsEmpty(final Set<Actor> actors) {
        return CollectionUtils.isEmpty(actors);
    }
}
