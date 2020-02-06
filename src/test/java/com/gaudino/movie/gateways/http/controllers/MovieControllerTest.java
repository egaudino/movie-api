package com.gaudino.movie.gateways.http.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gaudino.movie.domains.Censorship;
import com.gaudino.movie.gateways.http.resources.MovieRequest;
import com.gaudino.movie.utils.ActorBootstrap;
import com.gaudino.movie.utils.MovieBootstrap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mvc;

    @Spy
    private MovieBootstrap movieBootstrap;

    @Spy
    private ActorBootstrap actorBootstrap;


    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void shouldCreateMovieAndReturnCreatedStatus() throws Exception {
        MovieRequest movieRequest = new MovieRequest();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
        final String jsonAsString = objectMapper.writeValueAsString(MovieRequest.builder()
                .actors(actorBootstrap.buildActorsList())
                .director("Cristopher Nolan")
                .releaseDate(LocalDate.of(2017,7,17))
                .name("Dunkirk")
                .build());

        mvc.perform(MockMvcRequestBuilders
            .post("/api/v1/movie/create")
            .header("censorship", Censorship.CENSORED.getValue())
            .content(jsonAsString)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());

        mvc.perform(MockMvcRequestBuilders
            .get("/api/v1/movie")
            .header("name", "Dunkirk")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    }

}