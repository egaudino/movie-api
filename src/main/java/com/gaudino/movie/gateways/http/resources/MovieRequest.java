package com.gaudino.movie.gateways.http.resources;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gaudino.movie.domains.Actor;
import com.gaudino.movie.domains.Censorship;
import com.gaudino.movie.domains.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest {
    private String name;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate releaseDate;
    private String director;
    private Set<Actor> actors;

    public Movie toDomain(Censorship censorshipLevel) {
        return Movie.builder()
                .name(this.name)
                .releaseDate(this.releaseDate)
                .director(this.director)
                .censorship(censorshipLevel)
                .director(this.director)
                .actors(this.actors)
                .build();
    }
}
