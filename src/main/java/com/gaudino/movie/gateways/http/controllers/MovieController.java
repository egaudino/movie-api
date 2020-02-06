package com.gaudino.movie.gateways.http.controllers;

import com.gaudino.movie.domains.Censorship;
import com.gaudino.movie.domains.Movie;
import com.gaudino.movie.gateways.MovieGateway;
import com.gaudino.movie.gateways.http.resources.MovieRequest;
import com.gaudino.movie.usecases.CreateMovie;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movie")
public class MovieController {

    private final CreateMovie createMovie;
    private final MovieGateway movieGateway;

    @ApiOperation(value = "Create Movie")
    @PostMapping(value = "/create")
    public ResponseEntity<Movie> createMovie(@Valid @RequestBody MovieRequest movieRequest, @RequestHeader("censorship") Censorship censorship) {
        return new ResponseEntity<>(createMovie.execute(movieRequest.toDomain(censorship)), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get Movie By Name")
    @GetMapping
    public ResponseEntity<Movie> getMovieByName(@RequestHeader String name) {
        return new ResponseEntity<>(movieGateway.getMovieByName(name), HttpStatus.OK);
    }

    @ApiOperation(value = "Get movies by censorship level")
    @GetMapping(value = "/censorship")
    public List<Movie> getAllMoviesByCensorshipLevel(@RequestParam Censorship censorshipLevel) {
        return movieGateway.getMoviesByCensorshipLevel(censorshipLevel);
    }

}
