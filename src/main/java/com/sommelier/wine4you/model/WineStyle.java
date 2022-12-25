package com.sommelier.wine4you.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "styles")
public class WineStyle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name_style")
    private String nameStyle;

    public WineStyle(String nameStyle) {
        this.nameStyle = nameStyle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WineStyle wineStyle = (WineStyle) o;

        if (!Objects.equals(id, wineStyle.id)) return false;
        return Objects.equals(nameStyle, wineStyle.nameStyle);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nameStyle != null ? nameStyle.hashCode() : 0);
        return result;
    }
}
