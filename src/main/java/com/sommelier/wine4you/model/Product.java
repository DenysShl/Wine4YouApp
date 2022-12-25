package com.sommelier.wine4you.model;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "made_in_country")
    private String country;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "in_stock")
    private Boolean inStock;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Product product = (Product) o;

        if (!Objects.equals(id, product.id)) {
            return false;
        }
        if (!Objects.equals(brand, product.brand)) {
            return false;
        }
        if (!Objects.equals(country, product.country)) {
            return false;
        }
        if (!Objects.equals(title, product.title)) {
            return false;
        }
        if (!Objects.equals(price, product.price)) {
            return false;
        }
        return Objects.equals(inStock, product.inStock);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (inStock != null ? inStock.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{"
                + "id=" + id
                + ", brand='" + brand + '\''
                + ", country='" + country + '\''
                + ", title='" + title + '\''
                + ", price=" + price
                + ", inStock=" + inStock
                + '}';
    }
}
