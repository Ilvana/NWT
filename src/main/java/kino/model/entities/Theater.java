package kino.model.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "theater")
@Entity
public class Theater {

    private static final long serialVersionUID = 123456782L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "sizeX")
    private Integer sizeX;

    @Column(name = "sizeY")
    private Integer sizeY;

    public Theater() {
    }

    public Theater(String name, Integer sizeX, Integer sizeY) {
        this.name = name;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
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

    public Integer getSizeX() {
        return sizeX;
    }

    public void setSizeX(Integer sizeX) {
        this.sizeX = sizeX;
    }

    public Integer getSizeY() {
        return sizeY;
    }

    public void setSizeY(Integer sizeY) {
        this.sizeY = sizeY;
    }

    @Override
    public String toString() {
        return "Theater{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sizeX=" + sizeX +
                ", sizeY=" + sizeY +
                '}';
    }
}


