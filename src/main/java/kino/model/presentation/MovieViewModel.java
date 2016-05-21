package kino.model.presentation;

import kino.model.entities.Movie;

public class MovieViewModel {
    private Integer id;

    private String name;

    private String description;

    private Integer duration;

    private String director;

    private String genre;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public MovieViewModel(Movie movie) {
        id=movie.getId();
        name=movie.getName();
        description=movie.getDescription();
        duration=movie.getDuration();
        director=movie.getDirector();
        genre=movie.getGenre();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}

