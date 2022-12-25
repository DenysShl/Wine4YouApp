package com.sommelier.wine4you.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "tastes")
public class WineTaste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name_taste")
    private String nameTaste;

    public WineTaste(String nameTaste) {
        this.nameTaste = nameTaste;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WineTaste wineTaste = (WineTaste) o;

        if (!Objects.equals(id, wineTaste.id)) return false;
        return Objects.equals(nameTaste, wineTaste.nameTaste);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nameTaste != null ? nameTaste.hashCode() : 0);
        return result;
    }
}
