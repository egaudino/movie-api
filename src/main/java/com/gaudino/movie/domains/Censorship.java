package com.gaudino.movie.domains;

public enum Censorship {
    CENSORED("CENSORED"),
    NOT_CENSORED("NOT_CENSORED");

    private String value;

    Censorship(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }



}
