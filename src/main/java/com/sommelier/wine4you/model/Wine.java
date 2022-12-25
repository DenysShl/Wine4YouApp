package com.sommelier.wine4you.model;

import com.sommelier.wine4you.model.enums.WineType;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wines")
public class Wine extends Product {
    @Column(name = "name")
    private String name;

    @Column(name = "wine_type")
    @Enumerated(EnumType.STRING)
    private WineType wineType;
    @OneToOne
    private WineStyle wineStyle;

    @OneToOne
    private WineTaste wineTaste;

    @OneToOne
    private Event event;

    @Column(name = "capacity")
    private double capacity;
    @OneToMany(mappedBy = "wine", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Image> images = new HashSet<>();
    private String description;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Wine wine = (Wine) o;

        if (Double.compare(wine.capacity, capacity) != 0) {
            return false;
        }
        if (!Objects.equals(name, wine.name)) {
            return false;
        }
        if (wineStyle != wine.wineStyle) {
            return false;
        }
        if (wineType != wine.wineType) {
            return false;
        }
        if (wineTaste != wine.wineTaste) {
            return false;
        }
        if (event != wine.event) {
            return false;
        }
        if (!Objects.equals(images, wine.images)) {
            return false;
        }
        return Objects.equals(description, wine.description);
    }

    @Override
    public int hashCode() {
        int result;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (wineStyle != null ? wineStyle.hashCode() : 0);
        result = 31 * result + (wineType != null ? wineType.hashCode() : 0);
        result = 31 * result + (wineTaste != null ? wineTaste.hashCode() : 0);
        long temp;
        temp = Double.doubleToLongBits(capacity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (event != null ? event.hashCode() : 0);
        result = 31 * result + (images != null ? images.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Wine{"
                + "name='" + name + '\''
                + ", wineStyle=" + wineStyle
                + ", wineType=" + wineType
                + ", wineTaste=" + wineTaste
                + ", capacity=" + capacity
                + ", event=" + event
                + ", image=" + images
                + ", description='" + description + '\''
                + '}';
    }
}
