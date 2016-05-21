package kino.model.presentation;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import kino.model.entities.Theater;

public class TheaterViewModel {

    private Integer id;
    private String name;
    private Integer sizeX;
    private Integer sizeY;

    public TheaterViewModel() {
    }

    public TheaterViewModel(Theater theater) {
        id = theater.getId();
        name = theater.getName();
        sizeX = theater.getSizeX();
        sizeY = theater.getSizeY();
    }

    public Integer getId() {
        return id;
    }

    public Integer getSizeX() {
        return sizeX;
    }

    public String getName() {
        return name;
    }

    public Integer getSizeY() {
        return sizeY;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSizeX(Integer sizeX) {
        this.sizeX = sizeX;
    }

    public void setSizeY(Integer sizeY) {
        this.sizeY = sizeY;
    }
}
