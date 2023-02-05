package com.sommelier.wine4you.model;

import java.util.Arrays;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "wine_images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "url_path")
    private String urlPath;

    @Lob
    @Column(name = "image", unique = false, nullable = false, length = 100000)
    private byte[] content;
    @ManyToOne(targetEntity = Wine.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "wine_id", nullable = false)
    private Wine wine;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Image image = (Image) o;

        if (!Objects.equals(id, image.id)) {
            return false;
        }
        if (!Objects.equals(name, image.name)) {
            return false;
        }
        if (!Objects.equals(content, image.content)) {
            return false;
        }
        return Objects.equals(wine, image.wine);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (wine != null ? wine.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WineImage{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", content=" + Arrays.toString(content)
                + ", wine=" + wine
                + '}';
    }
}
