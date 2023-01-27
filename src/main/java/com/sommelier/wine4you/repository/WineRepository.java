package com.sommelier.wine4you.repository;

import com.sommelier.wine4you.model.Event;
import com.sommelier.wine4you.model.Meal;
import com.sommelier.wine4you.model.Wine;
import com.sommelier.wine4you.model.WineStyle;
import com.sommelier.wine4you.model.WineTaste;
import com.sommelier.wine4you.model.enums.WineType;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WineRepository extends JpaRepository<Wine, Long> {
    Optional<List<Wine>> findByBrand(String brand);

    Optional<List<Wine>> findByName(String name);

    Optional<List<Wine>> findByPriceBetween(BigDecimal priceMin, BigDecimal priceMax);

    Optional<List<Wine>> findByCountry(String country);

    Optional<List<Wine>> findByEvent(Event event);

    Optional<List<Wine>> findByWineStyle(WineStyle wineStyle);

    Optional<List<Wine>> findByWineType(WineType wineType);

    Optional<List<Wine>> findByWineTaste(WineTaste wineTaste);

    Optional<List<Wine>> findByMeal(Meal meal);
}
