package com.gaudino.movie.gateways.repositories;

import com.gaudino.movie.domains.Censorship;
import com.gaudino.movie.domains.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
    Movie findByName(String name);
    List<Movie> findByCensorship(Censorship censorshipLevel);
}
